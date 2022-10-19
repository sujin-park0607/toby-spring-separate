package com.likelion.daoClass;

import com.likelion.dao.daoClass.UerDAOClass;
import com.likelion.dao.daoInterface.UserDaoFactory;
import com.likelion.dao.daoInterface.UserDaoInterface;
import com.likelion.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class UerDAOTest {
    @Test
    void 클래스커넥션() throws SQLException, ClassNotFoundException {
        UserDaoInterface dao = new UserDaoFactory().awsUserDao();
        String id = "15";

        dao.add(new User(id,"Suhwan","789456123"));

        User selectedUser = dao.get(id);
        Assertions.assertEquals("Suhwan",selectedUser.getName());

    }

}