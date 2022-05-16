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
import java.math.BigDecimal;

@WebServlet(name = "TagEditServlet", value = "/tag/edit")
public class TagEditServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = GetUserByReq(req);
        if (!user.isAdmin()) {
            RespError(resp, "无权限");
            return;
        }

        String tidStr = req.getParameter("tid");
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String icon = req.getParameter("icon");

        int tid;

        try { tid = Integer.parseInt(tidStr); }
        catch (Exception e) { RespError(resp, "Tid 不合法");return; }

        // 查找 Tag
        TagBean tag = Query.getTagDAO().getTagByTid(tid);
        if (tag == null) {
            RespError(resp, "Tag 不存在");
            return;
        }

        tag.setName(name);
        tag.setDesc(desc);
        tag.setIcon(icon);

        // 修改 Tag
        if (Query.getTagDAO().updateTag(tag)) {
            RespSuccess(resp);
        } else {
            RespError(resp);
        }
    }
}
