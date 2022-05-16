package com.qwqaq.costwarden.query;

import com.qwqaq.costwarden.model.TagBean;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    private TagBean rsToBean(ResultSet rs) throws SQLException {
        TagBean tag = new TagBean();
        tag.setTid(rs.getInt("tid"));
        tag.setName(rs.getString("name"));
        tag.setDesc(rs.getString("desc"));
        tag.setIcon(rs.getString("icon"));
        return tag;
    }
}
