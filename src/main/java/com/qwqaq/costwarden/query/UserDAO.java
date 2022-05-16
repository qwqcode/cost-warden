package com.qwqaq.costwarden.query;

import com.qwqaq.costwarden.model.UserBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO {
    public UserBean getUserByName(String name) {
        UserBean user = null;

        try {
            ResultSet rs = this.select("SELECT * FROM users WHERE name = ? LIMIT 1", name);
            if (rs.next()) {
                user = rsToBean(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConn();
        }

        return user;
    }

    private UserBean rsToBean(ResultSet rs) throws SQLException {
        UserBean user = new UserBean();
        user.setUid(rs.getInt("uid"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setAdmin(rs.getInt("is_admin"));
        return user;
    }
}
