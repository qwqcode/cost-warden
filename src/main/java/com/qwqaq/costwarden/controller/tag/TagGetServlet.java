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
import java.util.ArrayList;

@WebServlet(name = "TagGetServlet", value = "/tag/get")
public class TagGetServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<TagBean> tags = Query.getTagDAO().getAllTags();

        RespData(resp, tags);
    }
}
