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

@WebServlet(name = "TagAddServlet", value = "/tag/add")
public class TagAddServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = GetUserByReq(req);
        if (!user.isAdmin()) {
            RespError(resp, "无权限");
            return;
        }

        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String icon = req.getParameter("icon");

        if (IsEmpty(name, desc, icon)) return;

        // 保存数据
        TagBean tag = new TagBean();
        tag.setName(name);
        tag.setDesc(desc);
        tag.setIcon(icon);

        Query.getTagDAO().createTag(tag);
    }
}
