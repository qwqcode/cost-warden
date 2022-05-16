package com.qwqaq.costwarden.controller.user;

import com.qwqaq.costwarden.controller.BaseController;
import com.qwqaq.costwarden.model.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserLogoutServlet", value = "/user/logout")
public class UserLogoutServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        if (user == null) {
            RespError(resp, "用户未登录，无需注销");
            return;
        }

        req.getSession().removeAttribute("user");
        RespSuccess(resp);
    }
}
