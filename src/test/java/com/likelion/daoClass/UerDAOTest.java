package com.likelion.daoClass;

import com.likelion.dao.daoInterface.AWSConnectionMaker;
import com.likelion.dao.daoInterface.ConnectionMaker;
import com.likelion.dao.daoInterface.UserDaoFactory;
import com.likelion.dao.daoInterface.UserDao;
import com.likelion.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UerDAOTest {

    @Autowired
    ApplicationContext context;

    @BeforeEach
    void setup() throws SQLException, ClassNotFoundException {
        UserDao dao = context.getBean("awsUserDao", UserDao.class);
        dao.deleteAll();
    }
    @Test
    void 클래스커넥션() throws SQLException, ClassNotFoundException {
        UserDao dao = context.getBean("awsUserDao", UserDao.class);
        String id = "23";

        dao.add(new User(id,"Suhwan","789456123"));

        User selectedUser = dao.get(id);
        Assertions.assertEquals("Suhwan",selectedUser.getName());

    }

    @Test
    void getCountTest() throws SQLException, ClassNotFoundException {
        UserDao dao = context.getBean("awsUserDao", UserDao.class);

        dao.add(new User("1","Suhwan","789456123"));
        Assertions.assertEquals(1,dao.getCount());

        dao.add(new User("2","Suhwan","789456123"));
        Assertions.assertEquals(1,dao.getCount());

        dao.add(new User("3","Suhwan","789456123"));
        Assertions.assertEquals(1,dao.getCount());
    }

    @Test
    void DeleteAllTest() throws SQLException, ClassNotFoundException {
        UserDao dao = context.getBean("awsUserDao", UserDao.class);
        String id = "24";
        dao.add(new User(id,"Suhwan","789456123"));
        dao.add(new User(id,"Sujin","1111"));

        dao.deleteAll();

        int count = dao.getCount();
        Assertions.assertEquals(0,count);
    }

}