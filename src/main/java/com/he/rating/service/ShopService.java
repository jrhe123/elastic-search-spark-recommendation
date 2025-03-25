package com.he.rating.service;

import com.he.rating.common.BusinessException;
import com.he.rating.model.ShopModel;

import java.math.BigDecimal;
import java.util.List;

public interface ShopService {

    ShopModel create(ShopModel shopModel) throws BusinessException;

    ShopModel get(Integer id);

    List<ShopModel> selectAll();

    Integer countAllShop();

    List<ShopModel> recommend(BigDecimal longitude, BigDecimal latitude);
}
