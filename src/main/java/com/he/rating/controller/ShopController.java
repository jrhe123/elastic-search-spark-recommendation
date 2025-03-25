package com.he.rating.controller;

import com.he.rating.common.BusinessException;
import com.he.rating.common.CommonRes;
import com.he.rating.common.EmBussinessError;
import com.he.rating.model.CategoryModel;
import com.he.rating.model.ShopModel;
import com.he.rating.service.CategoryService;
import com.he.rating.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("/shop")
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    // v1 recommend
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public CommonRes recommend(
            @RequestParam(name = "longitude", required = true) BigDecimal longitude,
            @RequestParam(name = "latitude", required = true) BigDecimal latitude
    ) throws BusinessException {

        if (longitude == null || latitude == null) {
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
        }

        List<ShopModel> shopModels = shopService.recommend(longitude, latitude);
        return CommonRes.create(shopModels);
    }

    // v1 search
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public CommonRes search(
            @RequestParam(name = "longitude", required = true) BigDecimal longitude,
            @RequestParam(name = "latitude", required = true) BigDecimal latitude,
            @RequestParam(name = "keyword", required = true) String keyword,
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            @RequestParam(name = "orderby", required = false) Integer orderby
    ) throws BusinessException {
        if (!StringUtils.hasLength(keyword) || longitude == null || latitude == null) {
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
        }

        List<ShopModel> shopModels = shopService.search(
                longitude, latitude, keyword, categoryId, orderby);
        List<CategoryModel> categoryModels = categoryService.selectAll();
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("shop", shopModels);
        resMap.put("category", categoryModels);
        return CommonRes.create(resMap);
    }
}
