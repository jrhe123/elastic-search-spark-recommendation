package com.he.rating.service;

import com.he.rating.common.BusinessException;
import com.he.rating.model.CategoryModel;

import java.util.List;

public interface CategoryService {

    CategoryModel create(CategoryModel categoryModel) throws BusinessException;

    CategoryModel get(Integer id);

    List<CategoryModel> selectAll();

    Integer countAllCategory();
}
