package com.qwqaq.costwarden.controller.cost;

import com.qwqaq.costwarden.controller.BaseController;
import com.qwqaq.costwarden.model.CostChartItemBean;
import com.qwqaq.costwarden.model.UserBean;
import com.qwqaq.costwarden.query.Query;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CostStatServlet", value = "/cost/stat")
public class CostStatServlet extends BaseController {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean user = GetUserByReq(request);

        CostStatRespData data = new CostStatRespData();
        data.setYearPie(Query.getCostDAO().getCostStatPie("year", user.getUid()));
        data.setMonthPie(Query.getCostDAO().getCostStatPie("month", user.getUid()));
        data.setPriceMonths(Query.getCostDAO().getCostStatMonths(user.getUid()));

        RespData(response, data);
    }
}

class CostStatRespData {
    private ArrayList<CostChartItemBean> monthPie;
    private ArrayList<CostChartItemBean> yearPie;
    private ArrayList<CostChartItemBean> priceMonths;

    public ArrayList<CostChartItemBean> getMonthPie() {
        return monthPie;
    }

    public void setMonthPie(ArrayList<CostChartItemBean> monthPie) {
        this.monthPie = monthPie;
    }

    public ArrayList<CostChartItemBean> getYearPie() {
        return yearPie;
    }

    public void setYearPie(ArrayList<CostChartItemBean> yearPie) {
        this.yearPie = yearPie;
    }

    public ArrayList<CostChartItemBean> getPriceMonths() {
        return priceMonths;
    }

    public void setPriceMonths(ArrayList<CostChartItemBean> priceMonths) {
        this.priceMonths = priceMonths;
    }
}
