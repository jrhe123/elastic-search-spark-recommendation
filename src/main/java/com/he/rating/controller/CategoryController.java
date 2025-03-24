package com.he.rating.controller;

import com.he.rating.common.CommonRes;
import com.he.rating.model.CategoryModel;
import com.he.rating.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/category")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public CommonRes list() {
        List<CategoryModel> categoryModels = categoryService.selectAll();
        return CommonRes.create(categoryModels);
    }
}
