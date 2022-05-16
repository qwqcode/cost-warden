package com.qwqaq.costwarden.controller;

import com.google.gson.Gson;
import com.qwqaq.costwarden.util.RespBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseController extends HttpServlet {
    public static boolean IsEmpty(final String... strings) {
        for (int i = 0; i < strings.length; i++) {
            if (IsEmpty(strings[i])) return true;
        }

        return false;
    }

    // 判断值是否为空 (不允许纯空格字符串)
    public static boolean IsEmpty(final String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean IsEmail(String email) {
        String emailRegx = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(emailRegx);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // 响应成功
    public static void RespSuccess(HttpServletResponse resp) throws IOException {
        Resp(resp, true, null, null);
    }

    public static void RespSuccess(HttpServletResponse resp, String msg) throws IOException {
        Resp(resp, true, msg, null);
    }

    // 响应数据
    public static void RespData(HttpServletResponse resp, Object data) throws IOException {
        Resp(resp, true, null, data);
    }

    // 响应错误
    public static void RespError(HttpServletResponse resp) throws IOException {
        Resp(resp, false, null, null);
    }

    public static void RespError(HttpServletResponse resp, String msg) throws IOException {
        Resp(resp, false, msg, null);
    }

    public static void RespError(HttpServletResponse resp, String msg, Object data) throws IOException {
        Resp(resp, false, msg, data);
    }

    private static void Resp(HttpServletResponse resp, Boolean success) throws IOException {
        Resp(resp, success, null, null);
    }

    // 响应 API JSON 数据
    private static void Resp(HttpServletResponse resp, Boolean success, String msg, Object data) throws IOException {
        RespBean respData = new RespBean();
        respData.setSuccess(success);
        respData.setMsg(msg);
        respData.setData(data);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(new Gson().toJson(respData));
        out.flush();
    }
}
