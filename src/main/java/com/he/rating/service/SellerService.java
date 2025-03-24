package com.he.rating.service;

import com.he.rating.common.BusinessException;
import com.he.rating.model.SellerModel;

import java.util.List;

public interface SellerService {

    SellerModel create(SellerModel sellerModel) throws BusinessException;

    SellerModel get(Integer id);

    List<SellerModel> selectAll();

    SellerModel changeStatus(Integer id, Integer disabledFlag);
}
