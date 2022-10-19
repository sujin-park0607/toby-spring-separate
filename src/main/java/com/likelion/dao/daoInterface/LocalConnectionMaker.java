package com.likelion.dao.daoInterface;

import java.sql.Connection;
import java.sql.SQLException;

public class LocalConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        return null;
    }
}
