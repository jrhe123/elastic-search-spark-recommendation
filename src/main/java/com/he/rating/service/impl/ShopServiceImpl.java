package com.he.rating.service.impl;

import com.he.rating.common.BusinessException;
import com.he.rating.common.EmBussinessError;
import com.he.rating.dao.ShopModelMapper;
import com.he.rating.model.CategoryModel;
import com.he.rating.model.SellerModel;
import com.he.rating.model.ShopModel;
import com.he.rating.service.CategoryService;
import com.he.rating.service.SellerService;
import com.he.rating.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopModelMapper shopModelMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerService sellerService;

    @Override
    @Transactional
    public ShopModel create(ShopModel shopModel) throws BusinessException {
        shopModel.setCreatedAt(new Date());
        shopModel.setUpdatedAt(new Date());

        // 1. validate seller
        SellerModel sellerModel = sellerService.get(shopModel.getSellerId());
        if (sellerModel == null) {
            throw new BusinessException(
                    EmBussinessError.PARAMETER_VALIDATION_ERROR, "Seller not found");
        }
        if (sellerModel.getDisabledFlag().intValue() == 1) {
            throw new BusinessException(
                    EmBussinessError.PARAMETER_VALIDATION_ERROR, "Seller is disabled");
        }

        // 2. validate category
        CategoryModel categoryModel = categoryService.get(shopModel.getCategoryId());
        if (categoryModel == null) {
            throw new BusinessException(
                    EmBussinessError.PARAMETER_VALIDATION_ERROR, "Category not found");
        }

        // 3. create shop now
        shopModelMapper.insertSelective(shopModel);

        return get(shopModel.getId());
    }

    @Override
    public ShopModel get(Integer id) {
        ShopModel shopModel = shopModelMapper.selectByPrimaryKey(id);
        if (shopModel == null) {
            return null;
        }

        // populate related objects with FK id (e.g., seller, category)
        shopModel.setSellerModel(
                sellerService.get(shopModel.getSellerId())
        );
        shopModel.setCategoryModel(
                categoryService.get(shopModel.getCategoryId())
        );

        return shopModel;
    }

    @Override
    public List<ShopModel> selectAll() {
        List<ShopModel> shopModels = shopModelMapper.selectAll();
        shopModels.forEach(shopModel -> {
            shopModel.setSellerModel(
                    sellerService.get(shopModel.getSellerId())
            );
            shopModel.setCategoryModel(
                    categoryService.get(shopModel.getCategoryId())
            );
        });
        return shopModels;
    }
}
