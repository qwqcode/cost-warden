package com.qwqaq.costwarden.query;

import com.qwqaq.costwarden.model.CostBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CostDAO extends BaseDAO {
    public ArrayList<CostBean> getCostsByUid(int uid) {
        ArrayList<CostBean> costs = new ArrayList<CostBean>();

        try {
            ResultSet rs = this.select("SELECT * FROM costs WHERE uid = ?", uid);
            while (rs.next()) {
                costs.add(rsToBean(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConn();
        }

        return costs;
    }

    private CostBean rsToBean(ResultSet rs) throws SQLException {
        CostBean cost = new CostBean();
        cost.setCid(rs.getInt("cid"));
        cost.setUid(rs.getInt("uid"));
        cost.setTid(rs.getInt("tid"));
        cost.setPrice(rs.getBigDecimal("price"));
        cost.setNote(rs.getString("note"));
        cost.setDate(rs.getString("date"));
        return cost;
    }
}
