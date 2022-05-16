package com.qwqaq.costwarden.controller.cost;

import com.qwqaq.costwarden.controller.BaseController;
import com.qwqaq.costwarden.model.CostBean;
import com.qwqaq.costwarden.model.UserBean;
import com.qwqaq.costwarden.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "CostAddServlet", value = "/cost/add")
public class CostAddServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = GetUserByReq(req);

        String priceStr = req.getParameter("price");
        String tidStr = req.getParameter("tid");
        String note = req.getParameter("note");

        if (IsEmpty(priceStr, tidStr)) return;

        BigDecimal price;
        int tid;

        // 类型转换
        try { price = new BigDecimal(priceStr); }
        catch (Exception e) { RespError(resp, "价格参数不合法");return; }

        try { tid = Integer.parseInt(tidStr); }
        catch (Exception e) { RespError(resp, "tid 不合法");return; }

        // 查找 Tag
        if (Query.getTagDAO().getTagByTid(tid) == null) {
            RespError(resp, "Tag 不存在");
            return;
        }

        // 保存数据
        CostBean cost = new CostBean();
        cost.setPrice(price);
        cost.setTid(tid);
        cost.setNote(note);
        cost.setUid(user.getUid());

        Query.getCostDAO().createCost(cost);
    }
}
