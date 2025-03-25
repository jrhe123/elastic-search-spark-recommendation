package com.he.rating.service;

import com.he.rating.common.BusinessException;
import com.he.rating.model.ShopModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ShopService {

    ShopModel create(ShopModel shopModel) throws BusinessException;

    ShopModel get(Integer id);

    List<ShopModel> selectAll();

    Integer countAllShop();

    List<ShopModel> recommend(BigDecimal longitude, BigDecimal latitude);

    List<ShopModel> search(
            BigDecimal longitude, BigDecimal latitude, String keyword,
            Integer orderby, Integer categoryId, String tags);

    List<Map<String, Object>> searchGroupByTags(
            String keyword, Integer categoryId, String tags);
}
