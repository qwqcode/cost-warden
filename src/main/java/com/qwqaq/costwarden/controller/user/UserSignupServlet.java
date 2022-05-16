package com.qwqaq.costwarden.controller.user;

import com.qwqaq.costwarden.controller.BaseController;
import com.qwqaq.costwarden.model.UserBean;
import com.qwqaq.costwarden.query.Query;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserSignupServlet", value = "/user/signup")
public class UserSignupServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re_password");

        if (IsEmpty(username, email, password, rePassword)) {
             RespError(resp, "请求参数不完整");
             return;
        }

        if (!IsEmail(email)) {
            RespError(resp, "邮箱格式有误");
            return;
        }

        if (!rePassword.equals(password)) {
            RespError(resp, "确认密码输入不正确");
            return;
        }

        if (Query.getUserDAO().getUserByName(username) != null) {
            RespError(resp, "用户名已存在");
            return;
        }

        if (Query.getUserDAO().getUserByEmail(email) != null) {
            RespError(resp, "邮箱已被注册");
            return;
        }

        UserBean user = new UserBean();
        user.setName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setAdmin(false);

        if (!Query.getUserDAO().createUser(user)) {
            RespError(resp, "注册失败");
            return;
        }

        RespSuccess(resp);
    }
}
