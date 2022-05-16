package com.qwqaq.costwarden.query;

import com.qwqaq.costwarden.model.CostBean;
import com.qwqaq.costwarden.model.TagBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class TagDAO extends BaseDAO {
    public TagBean getTagByName(String name) {
        TagBean tag = null;

        try {
            ResultSet rs = this.select("SELECT * FROM tags WHERE name = ? LIMIT 1", name);
            if (rs.next()) {
                tag = rsToBean(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConn();
        }

        return tag;
    }

    public boolean createTag(TagBean tag) {
        try {
            int rec = this.update("INSERT INTO tags "
                            + "(name, desc, icon) VALUES "
                            + "(?, ?, ?)",
                    tag.getName(), tag.getDesc(), tag.getIcon());
            return (rec == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConn();
        }
    }

    public boolean updateTag(TagBean tag) {
        try {
            int rec = this.update("UPDATE tags "
                            + "SET name = ?, desc = ?, icon = ? "
                            + "WHERE tid = ?",
                    tag.getName(), tag.getDesc(), tag.getIcon(),
                    tag.getTid());
            return (rec == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConn();
        }
    }

    public boolean delTagByTid(int tid) {
        try {
            int rec = this.update("DELETE FROM tags WHERE tid = ?", tid);
            return (rec == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConn();
        }
    }

    private TagBean rsToBean(ResultSet rs) throws SQLException {
        TagBean tag = new TagBean();
        tag.setTid(rs.getInt("tid"));
        tag.setName(rs.getString("name"));
        tag.setDesc(rs.getString("desc"));
        tag.setIcon(rs.getString("icon"));
        return tag;
    }
}
