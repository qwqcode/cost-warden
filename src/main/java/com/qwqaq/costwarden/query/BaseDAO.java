package com.qwqaq.costwarden.query;

import java.sql.*;

public class BaseDAO {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * 连接数据库
     * @return 执行是否成功
     */
    protected boolean openConn() {
        // TODO 改为 properties
        String url = "jdbc:mysql://localhost:3306/costwarden?characterEncoding=UTF8&useSSL=false";
        String user = "root";
        String password = "123456";
        // @note 由于安全策略默认开启 SSL，需添加 &useSSL=false 关闭以满足测试环境要求

        try {
            Class.forName("com.mysql.jdbc.Driver"); // 加载驱动
            conn = DriverManager.getConnection(url, user, password); // 建立连接
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询操作
     * @param sql SQL 语句
     * @param args 参数
     * @return 查询结果
     */
    protected ResultSet select(String sql, Object... args){
        openConn();
        try {
            // 使用 PrepareStatement 预编译 SQL，防止 SQL 注入
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 增删改操作
     *
     * 执行 INSERT、UPDATE 或 DELETE 语句时使用
     * @param sql SQL 语句
     * @param args 参数
     * @return 更新记录的条数
     */
    protected int update(String sql, Object... args){
        openConn();
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 释放资源
     * @return 执行是否成功
     */
    protected boolean closeConn() {
        try {
            if (!rs.isClosed()) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        try {
            if (!ps.isClosed()) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        try {
            if (!conn.isClosed()) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
