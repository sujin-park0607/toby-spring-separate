package com.likelion.daoClass;

import com.likelion.dao.daoInterface.UserDaoFactory;
import com.likelion.dao.daoInterface.UserDao;
import com.likelion.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class UerDAOTest {
    @Test
    void 클래스커넥션() throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDaoFactory().awsUserDao();
        String id = "15";

        dao.add(new User(id,"Suhwan","789456123"));

        User selectedUser = dao.get(id);
        Assertions.assertEquals("Suhwan",selectedUser.getName());

    }

}