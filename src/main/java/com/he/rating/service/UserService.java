package com.he.rating.service;

import com.he.rating.common.BusinessException;
import com.he.rating.model.UserModel;

import java.security.NoSuchAlgorithmException;

public interface UserService {

    UserModel getUser(Integer id);

    UserModel register(UserModel registerUser) throws BusinessException, NoSuchAlgorithmException;

    UserModel login(String telephone, String password) throws NoSuchAlgorithmException, BusinessException;

    Integer countAllUser();
}
