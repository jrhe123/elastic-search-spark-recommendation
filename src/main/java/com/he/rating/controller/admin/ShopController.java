package com.he.rating.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.rating.aspect.AdminPermission;
import com.he.rating.common.BusinessException;
import com.he.rating.common.CommonUtil;
import com.he.rating.common.EmBussinessError;
import com.he.rating.model.ShopModel;
import com.he.rating.request.CategoryCreateReq;
import com.he.rating.request.PageQuery;
import com.he.rating.request.ShopCreateReq;
import com.he.rating.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;
    
    @RequestMapping("/index")
    @AdminPermission(produceType = "text/html")
    public ModelAndView index(
            PageQuery pageQuery
    ) {
        ModelAndView mav = new ModelAndView("admin/shop/index.html");

        PageHelper.startPage(
                pageQuery.getPage(), pageQuery.getSize()
        );
        List<ShopModel> shopModels = shopService.selectAll();
        PageInfo<ShopModel> shopModelPageInfo = new PageInfo<>(shopModels);

        mav.addObject("data", shopModelPageInfo);
        mav.addObject("CONTROLLER_NAME", "shop");
        mav.addObject("ACTION_NAME", "index");
        return mav;
    }

    @RequestMapping("/create_page")
    @AdminPermission(produceType = "text/html")
    public ModelAndView createPage() {
        ModelAndView mav = new ModelAndView("admin/shop/create.html");

        mav.addObject("CONTROLLER_NAME", "shop");
        mav.addObject("ACTION_NAME", "create");
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission(produceType = "text/html")
    public String create(
            @Valid ShopCreateReq shopCreateReq,
            BindingResult bindingResult
    ) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(
                    EmBussinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult)
            );
        }

        ShopModel shopModel = new ShopModel();
        BeanUtils.copyProperties(shopCreateReq, shopModel);
        shopService.create(shopModel);

        return "redirect:/admin/shop/index";
    }

}
