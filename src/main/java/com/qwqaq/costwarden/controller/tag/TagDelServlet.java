package com.qwqaq.costwarden.controller.tag;

import com.qwqaq.costwarden.controller.BaseController;
import com.qwqaq.costwarden.model.CostBean;
import com.qwqaq.costwarden.model.TagBean;
import com.qwqaq.costwarden.model.UserBean;
import com.qwqaq.costwarden.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TagDelServlet", value = "/tag/del")
public class TagDelServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = GetUserByReq(req);
        if (!user.isAdmin()) {
            RespError(resp, "无权限");
            return;
        }

        String tidStr = req.getParameter("tid");
        int tid;

        try { tid = Integer.parseInt(tidStr);}
        catch (Exception e) { RespError(resp, "tid 不合法");return; }

        // 查找 Tag
        TagBean tag = Query.getTagDAO().getTagByTid(tid);
        if (tag == null) {
            RespError(resp, "Tag 不存在");
            return;
        }

        // 删除 Tag
        if (Query.getTagDAO().delTagByTid(tid)) {
            RespSuccess(resp);
        } else {
            RespError(resp);
        }
    }
}
