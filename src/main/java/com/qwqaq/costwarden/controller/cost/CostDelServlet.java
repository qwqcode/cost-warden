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

@WebServlet(name = "CostDelServlet", value = "/cost/del")
public class CostDelServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = GetUserByReq(req);
        String cidStr = req.getParameter("cid");
        int cid;

        try { cid = Integer.parseInt(cidStr);}
        catch (Exception e) { RespError(resp, "cid 不合法");return; }

        // 查找 Cost
        CostBean cost = Query.getCostDAO().getCostByCid(cid);
        if (cost == null) {
            RespError(resp, "Cost 不存在");
            return;
        }

        if (cost.getUid() != user.getUid()) {
            RespError(resp, "无权删除");
            return;
        }

        // 删除 Cost
        if (Query.getCostDAO().delCostByCid(cid)) {
            RespSuccess(resp);
        } else {
            RespError(resp);
        }
    }
}
