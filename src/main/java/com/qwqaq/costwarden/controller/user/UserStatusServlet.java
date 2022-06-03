package com.qwqaq.costwarden.controller.user;

import com.qwqaq.costwarden.controller.BaseController;
import com.qwqaq.costwarden.model.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserStatusServlet", value = "/user/status")
public class UserStatusServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStatusBean data = new UserStatusBean();
        data.setLogin(GetUserByReq(req) != null);

        RespData(resp, data);
    }
}

class UserStatusBean {
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}