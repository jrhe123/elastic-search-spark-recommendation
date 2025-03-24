package com.he.rating.service.impl;

import com.he.rating.common.BusinessException;
import com.he.rating.dao.SellerModelMapper;
import com.he.rating.model.SellerModel;
import com.he.rating.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerModelMapper sellerModelMapper;


    @Override
    public SellerModel create(SellerModel sellerModel) throws BusinessException {
        return null;
    }

    @Override
    public SellerModel get(Integer id) {
        return null;
    }

    @Override
    public List<SellerModel> selectAll() {
        return sellerModelMapper.selectAll();
    }

    @Override
    public SellerModel changeStatus(Integer id, Integer disabledFlag) {
        return null;
    }
}
