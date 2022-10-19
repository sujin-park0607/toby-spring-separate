package com.likelion.dao.daoInterface;

public class UserDaoFactory {
    //조립
    public UserDao awsUserDao(){
        AWSConnectionMaker awsConnectionMaker = new AWSConnectionMaker();
        UserDao userDao = new UserDao(awsConnectionMaker);
        return userDao;
    }

}
