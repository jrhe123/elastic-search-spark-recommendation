package com.he.rating.service.impl;

import com.he.rating.common.BusinessException;
import com.he.rating.common.EmBussinessError;
import com.he.rating.dao.UserModelMapper;
import com.he.rating.model.UserModel;
import com.he.rating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public UserModel register(UserModel registerUser) throws BusinessException, NoSuchAlgorithmException {
        registerUser.setCreatedAt(new Date());
        registerUser.setUpdatedAt(new Date());
        registerUser.setPassword(
                encodeByMd5(registerUser.getPassword())
        );

        try {
            userModelMapper.insertSelective(registerUser);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(EmBussinessError.REGISTER_DUP_FAIL);
        }

        return getUser(registerUser.getId());
    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hash = digest.digest(
                str.getBytes(StandardCharsets.UTF_8)
        );
        return Base64.getEncoder().encodeToString(hash);
    }
}
