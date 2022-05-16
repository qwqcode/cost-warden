package com.qwqaq.costwarden.query;

import com.qwqaq.costwarden.model.UserBean;

import java.sql.ResultSet;

public class UserDAO extends BaseDAO {
    public UserBean getUserByName(String name) {
        UserBean user = new UserBean();

        try {
            ResultSet rs = this.select("SELECT * FROM users WHERE name = ?", name);
            if (rs.next()) {
                user.setUid(rs.getInt("uid"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getInt("is_admin"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConn();
        }

        return user;
    }
}
