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

@WebServlet(name = "CostEditServlet", value = "/cost/edit")
public class CostEditServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = GetUserByReq(req);
        String cidStr = req.getParameter("cid");
        String priceStr = req.getParameter("price");
        String tidStr = req.getParameter("tid");
        String note = req.getParameter("note");

        BigDecimal price;
        int cid;
        int tid;

        try { price = new BigDecimal(priceStr); }
        catch (Exception e) { RespError(resp, "价格参数不合法");return; }

        try { cid = Integer.parseInt(cidStr); }
        catch (Exception e) { RespError(resp, "cid 不合法");return; }

        try { tid = Integer.parseInt(tidStr); }
        catch (Exception e) { RespError(resp, "tid 不合法");return; }

        // 查找 Cost
        CostBean cost = Query.getCostDAO().getCostByCid(cid);
        if (cost == null) {
            RespError(resp, "Cost 不存在");
            return;
        }

        if (cost.getUid() != user.getUid()) {
            RespError(resp, "无权修改");
            return;
        }

        // 查找 Tag
        if (Query.getTagDAO().getTagByTid(tid) == null) {
            RespError(resp, "Tag 不存在");
            return;
        }

        cost.setPrice(price);
        cost.setTid(tid);
        cost.setNote(note);

        // 修改 Cost
        if (Query.getCostDAO().updateCost(cost)) {
            RespSuccess(resp);
        } else {
            RespError(resp);
        }
    }
}
