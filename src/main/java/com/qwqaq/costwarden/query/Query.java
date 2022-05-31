package com.qwqaq.costwarden.query;

public class Query {
    public static CostDAO getCostDAO() {
        return new CostDAO();
    }

    public static TagDAO getTagDAO() {
        return new TagDAO();
    }

    public static UserDAO getUserDAO() {
        return new UserDAO();
    }
}
