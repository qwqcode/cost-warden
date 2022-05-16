package com.qwqaq.costwarden.controller.user;

import com.qwqaq.costwarden.controller.BaseController;
import com.qwqaq.costwarden.model.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserGetServlet", value = "/user/get")
public class UserGetServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = GetUserByReq(req);
        if (user == null) {
            RespError(resp, "未登录，请先登录");
            return;
        }

        RespData(resp, user);
    }
}

