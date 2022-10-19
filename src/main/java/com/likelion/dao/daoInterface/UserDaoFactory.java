package com.likelion.dao.daoInterface;

import com.likelion.domain.User;

public class UserDaoFactory {
    //조립
    public UserDaoInterface awsUserDao(){
        AWSConnectionMaker awsConnectionMaker = new AWSConnectionMaker();
        UserDaoInterface userDao = new UserDaoInterface(awsConnectionMaker);
        return userDao;
    }

}
