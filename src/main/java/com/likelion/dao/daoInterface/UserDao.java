package com.likelion.dao.daoInterface;

import com.likelion.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final DataSource dataSource;
    private final JdbcContext jdbcContext;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext(dataSource);
    }

    public void deleteAll() throws SQLException{
        executeSql("delete from users");
    }

    private void executeSql(final String query) throws SQLException{
        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        return c.prepareStatement(query);
                    }
                }
        );
    }


    public void add(User user) throws SQLException, ClassNotFoundException {
        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy() {
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        PreparedStatement ps = c.prepareStatement("INSERT INTO users(id, name, password) VALUES (?, ?, ?)");
                        ps.setString(1, user.getId());
                        ps.setString(2, user.getName());
                        ps.setString(3, user.getPassword());

                        return ps;
                    }
                }
        );
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection conn = null;

        conn = dataSource.getConnection();

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
        Connection conn = null;
        conn = dataSource.getConnection();
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
