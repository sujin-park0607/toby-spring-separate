package com.likelion.dao.daoClass;

import com.likelion.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UerDAOClass {
    private SimpleConnectionMaker simpleConnectionMaker;

    public UerDAOClass() {
        simpleConnectionMaker = new SimpleConnectionMaker();
    }

    public void add(User user) throws SQLException {
        Connection conn = simpleConnectionMaker.makeNewConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO user(id, name, password) VALUES (?, ?, ?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public User get(String id) throws SQLException {
        Connection conn = simpleConnectionMaker.makeNewConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        ps.close();
        conn.close();
        rs.close();

        return user;
    }
}
