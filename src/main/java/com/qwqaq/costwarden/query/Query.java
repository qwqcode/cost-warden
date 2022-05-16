package com.qwqaq.costwarden.query;

// 使用单例模式控制实例的数目，
// 提供全局唯一 DAO 实例访问点
public class Query {
    // 饿汉式单例
    private static CostDAO costDAO = new CostDAO();
    private static TagDAO tagDAO = new TagDAO();
    private static UserDAO userDAO = new UserDAO();

    public static CostDAO getCostDAO() {
        return costDAO;
    }

    public static TagDAO getTagDAO() {
        return tagDAO;
    }

    public static UserDAO getUserDAO() {
        return userDAO;
    }
}
