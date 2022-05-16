package com.qwqaq.costwarden.query;

import com.qwqaq.costwarden.model.TagBean;
import com.qwqaq.costwarden.model.UserBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO {
    public UserBean getUserByNameOrEmail(String name, String email) {
        UserBean user = null;

        try {
            ResultSet rs = this.select("SELECT * FROM users WHERE LOWER(name) = LOWER(?) OR LOWER(email) = LOWER(?) LIMIT 1", name, email);
            if (rs.next()) user = rsToBean(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConn();
        }

        return user;
    }

    public UserBean getUserByName(String name) {
        UserBean user = null;

        try {
            ResultSet rs = this.select("SELECT * FROM users WHERE LOWER(name) = LOWER(?) LIMIT 1", name);
            if (rs.next()) user = rsToBean(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConn();
        }

        return user;
    }

    public UserBean getUserByEmail(String email) {
        UserBean user = null;

        try {
            ResultSet rs = this.select("SELECT * FROM users WHERE LOWER(email) = LOWER(?) LIMIT 1", email);
            if (rs.next()) user = rsToBean(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConn();
        }

        return user;
    }

    public boolean createUser(UserBean user) {
        try {
            int rec = this.update("INSERT INTO users "
                            + "(email, name, password, is_admin) VALUES "
                            + "(?, ?, ?, ?)",
                    user.getEmail(), user.getName(), user.getPassword(), (user.isAdmin() ? 1 : 0));
            return (rec == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConn();
        }
    }

    public boolean updateUser(UserBean user) {
        try {
            int rec = this.update("UPDATE users "
                            + "SET email = ?, name = ?, password = ?, is_admin = ? "
                            + "WHERE uid = ?",
                    user.getEmail(), user.getName(), user.getPassword(), (user.isAdmin() ? 1 : 0),
                    user.getUid());
            return (rec == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConn();
        }
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
