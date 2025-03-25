package com.he.rating.controller;

import com.he.rating.common.BusinessException;
import com.he.rating.common.CommonRes;
import com.he.rating.common.EmBussinessError;
import com.he.rating.model.ShopModel;
import com.he.rating.service.CategoryService;
import com.he.rating.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    // v1
    @RequestMapping(value = "/recommend", method = RequestMethod.POST)
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

}
