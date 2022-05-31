package com.qwqaq.costwarden.controller.cost;

import com.qwqaq.costwarden.controller.BaseController;
import com.qwqaq.costwarden.query.Query;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CostStatServlet", value = "/cost/stat")
public class CostStatServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RespData(response, Query.getCostDAO().getCostStatData());
    }
}
