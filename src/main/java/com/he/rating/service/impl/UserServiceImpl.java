package com.he.rating.service.impl;

import com.he.rating.dao.UserModelMapper;
import com.he.rating.model.UserModel;
import com.he.rating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }
}
