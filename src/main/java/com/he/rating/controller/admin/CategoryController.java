package com.he.rating.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.rating.aspect.AdminPermission;
import com.he.rating.common.BusinessException;
import com.he.rating.common.CommonRes;
import com.he.rating.common.CommonUtil;
import com.he.rating.common.EmBussinessError;
import com.he.rating.model.CategoryModel;
import com.he.rating.request.CategoryCreateReq;
import com.he.rating.request.PageQuery;
import com.he.rating.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/index")
    @AdminPermission(produceType = "text/html")
    public ModelAndView index(
            PageQuery pageQuery
    ) {
        ModelAndView mav = new ModelAndView("admin/category/index.html");

        PageHelper.startPage(
                pageQuery.getPage(), pageQuery.getSize()
        );
        List<CategoryModel> categoryModels = categoryService.selectAll();
        PageInfo<CategoryModel> categoryModelPageInfo = new PageInfo<>(categoryModels);

        mav.addObject("data", categoryModelPageInfo);
        mav.addObject("CONTROLLER_NAME", "category");
        mav.addObject("ACTION_NAME", "index");
        return mav;
    }

    @RequestMapping("/create_page")
    @AdminPermission(produceType = "text/html")
    public ModelAndView createPage() {
        ModelAndView mav = new ModelAndView("admin/category/create.html");

        mav.addObject("CONTROLLER_NAME", "category");
        mav.addObject("ACTION_NAME", "create");
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission(produceType = "text/html")
    public String create(
            @Valid CategoryCreateReq caetgoryCreateReq,
            BindingResult bindingResult
    ) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(
                    EmBussinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult)
            );
        }

        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(caetgoryCreateReq, categoryModel);
        categoryService.create(categoryModel);

        return "redirect:/admin/category/index";
    }

}
