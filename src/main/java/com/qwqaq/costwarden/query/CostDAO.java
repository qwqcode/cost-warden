package com.qwqaq.costwarden.query;

import com.google.gson.Gson;
import com.qwqaq.costwarden.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class CostDAO extends BaseDAO {
    private ArrayList<CostBean> costsSelectQuery(String sql, Object ...args) {
        ArrayList<CostBean> costs = new ArrayList<CostBean>();

        try {
            ResultSet rs = this.select(sql, args);
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

    public ArrayList<CostBean> getCostsByUid(int uid) {
        return costsSelectQuery("SELECT * FROM costs WHERE uid = ? ORDER BY date DESC", uid);
    }

    public ArrayList<CostBean> getCostsByConditions(int uid, String conditionsJson) {
        if (conditionsJson == null) return getCostsByUid(uid);

        CostFilterBean[] condArr;
        try {
            condArr = new Gson().fromJson(conditionsJson, CostFilterBean[].class);
        } catch (Exception e) {
            return getCostsByUid(uid);
        }

        ArrayList<Object> args = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM costs WHERE uid = ?");
        args.add(uid);

        for (CostFilterBean cond : condArr) {
            switch (cond.getName()) {
                case "year":
                    sql.append(" AND YEAR(date) = ?");
                    args.add(cond.getValue());
                    break;
                case "month":
                    sql.append(" AND MONTH(date) = ?");
                    args.add(cond.getValue());
                    break;
                case "day":
                    sql.append(" AND DAY(date) = ?");
                    args.add(cond.getValue());
                    break;
                case "note":
                    sql.append(" AND note LIKE ?");
                    args.add("%"+cond.getValue()+"%");
                    break;
                case "tid":
                    sql.append(" AND tid = ?");
                    args.add(cond.getValue());
                    break;
            }
        }

        sql.append(" ORDER BY date DESC");
        return costsSelectQuery(sql.toString(), args.toArray());
    }

    public ArrayList<CostBean> getCostsByNote(int note, int uid) {
        return costsSelectQuery("SELECT * FROM costs WHERE uid = ? AND note LIKE ? ORDER BY date DESC", uid, "%"+note+"%");
    }

    public ArrayList<CostBean> getCostsByYear(int year, int uid) {
        return costsSelectQuery("SELECT * FROM costs WHERE uid = ? AND YEAR(date) = ? ORDER BY date DESC", uid, year);
    }

    public ArrayList<CostBean> getCostsByMonth(int year, int month, int uid) {
        return costsSelectQuery("SELECT * FROM costs WHERE uid = ? AND YEAR(date) = ? AND MONTH(date) = ? ORDER BY date DESC", uid, year, month);
    }

    public ArrayList<CostBean> getCostsByDay(int year, int month, int day, int uid) {
        return costsSelectQuery("SELECT * FROM costs WHERE uid = ? AND YEAR(date) = ? AND MONTH(date) = ? AND DAY(date) = ? ORDER BY date DESC", uid, year, month, day);
    }

    public CostBean getCostByCid(int cid) {
        CostBean cost = null;

        try {
            ResultSet rs = this.select("SELECT * FROM costs WHERE cid = ? LIMIT 1", cid);
            if (rs.next()) {
                cost = rsToBean(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConn();
        }

        return cost;
    }

    public boolean createCost(CostBean cost) {
        Timestamp curtTime = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());

        try {
            int rec = this.update("INSERT INTO costs "
                            + "(uid, tid, price, note, date) VALUES "
                            + "(?, ?, ?, ?, ?)",
                    cost.getUid(), cost.getTid(), cost.getPrice(), cost.getNote(), curtTime);
            return (rec == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConn();
        }
    }

    public boolean delCostByCid(int cid) {
        try {
            int rec = this.update("DELETE FROM costs WHERE cid = ?", cid);
            return (rec == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConn();
        }
    }

    public boolean updateCost(CostBean cost) {
        try {
            int rec = this.update("UPDATE costs "
                            + "SET uid = ?, tid = ?, price = ?, note = ?, date = ? "
                            + "WHERE cid = ?",
                    cost.getUid(), cost.getTid(), cost.getPrice(), cost.getNote(), cost.getDate(),
                    cost.getCid());
            return (rec == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConn();
        }
    }

    public ArrayList<CostStatBean> getCostStatData() {
        ArrayList<CostStatBean> arr = new ArrayList<>();

        try {
            ResultSet rs = this.select("SELECT t.`name` as 'name', SUM(price) as 'value' " +
                    "FROM costs c " +
                    "JOIN tags t ON c.tid = t.tid " +
                    "WHERE YEAR(c.date) = '2022' " +
                    "GROUP BY t.`name` " +
                    "ORDER BY `value` DESC");
            while (rs.next()) {
                CostStatBean item = new CostStatBean();
                item.setName(rs.getString("name"));
                item.setValue(rs.getString("value"));
                arr.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConn();
        }

        return arr;
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
