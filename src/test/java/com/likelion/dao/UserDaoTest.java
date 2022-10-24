package com.likelion.dao;


import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UerDaoTest {

    @Autowired
    private ApplicationContext context;

    UserDao dao;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setup() throws SQLException, ClassNotFoundException {
        this.dao = this.context.getBean("awsUserDao", UserDao.class);
        this.user1 = new User("1","Suhwan","789456123");
        this.user2 = new User("2","Sujin","11111");
        this.user3 = new User("3","JoWon","55555");
        dao.deleteAll();
    }
    @Test
    void addAndget() throws SQLException, ClassNotFoundException {
        dao.add(user1);

        User selectedUser = dao.get("1");
        assertEquals("Suhwan",selectedUser.getName());
    }

    @Test
    void getTest(){
        assertThrows(EmptyResultDataAccessException.class, ()->
                dao.get("1"));
    }

    @Test
    void getCountTest() throws SQLException, ClassNotFoundException {
        assertEquals(0,dao.getCount());

        dao.add(user1);
        assertEquals(1,dao.getCount());

        dao.add(user2);
        assertEquals(2,dao.getCount());

        dao.add(user3);
        assertEquals(3,dao.getCount());
    }

    @Test
    void DeleteAllTest() throws SQLException, ClassNotFoundException {
        dao.add(user1);
        dao.add(user2);

        dao.deleteAll();

        int count = dao.getCount();
        assertEquals(0,count);
    }

}