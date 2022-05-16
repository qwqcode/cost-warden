package com.qwqaq.costwarden.controller.user;

import com.qwqaq.costwarden.controller.BaseController;
import com.qwqaq.costwarden.model.UserBean;
import com.qwqaq.costwarden.query.Query;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet", value = "/user/login")
public class UserLoginServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (IsEmpty(username, password)) {
             RespError(resp, "请求参数不完整");
             return;
        }

        UserBean user = Query.getUserDAO().getUserByNameOrEmail(username, username);
        if (user == null || !password.equals(user.getPassword())) {
            RespError(resp, "用户名或密码有误");
            return;
        }

        // Session 存储用户信息
        req.getSession().setAttribute("user", user);

        RespSuccess(resp);
    }
}
