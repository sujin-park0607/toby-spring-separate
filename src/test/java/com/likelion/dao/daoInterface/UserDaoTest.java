package com.likelion.dao.daoInterface;


import com.likelion.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UerDaoTest {
    UserDao dao;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    void setup() throws SQLException, ClassNotFoundException {
        dao = context.getBean("awsUserDao", UserDao.class);
        dao.deleteAll();
    }
    @Test
    void 클래스커넥션() throws SQLException, ClassNotFoundException {
        String id = "23";

        dao.add(new User(id,"Suhwan","789456123"));

        User selectedUser = dao.get(id);
        assertEquals("Suhwan",selectedUser.getName());

    }

    @Test
    void getCountTest() throws SQLException, ClassNotFoundException {
        assertEquals(0,dao.getCount());

        dao.add(new User("1","Suhwan","789456123"));
        assertEquals(1,dao.getCount());

        dao.add(new User("2","Suhwan","789456123"));
        assertEquals(2,dao.getCount());

        dao.add(new User("3","Suhwan","789456123"));
        assertEquals(3,dao.getCount());
    }

    @Test
    void DeleteAllTest() throws SQLException, ClassNotFoundException {
        dao.add(new User("1","Suhwan","789456123"));
        dao.add(new User("2","Sujin","1111"));

        dao.deleteAll();

        int count = dao.getCount();
        assertEquals(0,count);
    }

}