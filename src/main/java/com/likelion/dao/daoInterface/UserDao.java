package com.likelion.dao.daoInterface;

import com.likelion.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = connectionMaker.makeConnection();
            ps = stmt.makePreparedStatement(conn);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally { //에러가 나도 실행되는 블럭
            if(ps != null){ try{ ps.close(); }catch (SQLException e){ } }
            if(conn != null){ try{ conn.close(); }catch (SQLException e){ }}
        }
    }


    public void add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy st = new AddStrategy(user);
        jdbcContextWithStatementStrategy(st);
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        StatementStrategy st = new DeteAllStrategy();
        jdbcContextWithStatementStrategy(st);
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
            conn = connectionMaker.makeConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            User user = null;
            if(rs.next()){
                user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }

            ps.close();
            conn.close();
            rs.close();
            System.out.println("user:"+user);

            if(user == null) throw new EmptyResultDataAccessException(1);

            return user;

    }


    public int getCount() throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        conn.close();

        return count;
    }
}
