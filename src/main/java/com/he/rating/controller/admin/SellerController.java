package com.he.rating.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.rating.aspect.AdminPermission;
import com.he.rating.common.BusinessException;
import com.he.rating.common.CommonRes;
import com.he.rating.common.CommonUtil;
import com.he.rating.common.EmBussinessError;
import com.he.rating.model.SellerModel;
import com.he.rating.request.PageQuery;
import com.he.rating.request.SellerCreateReq;
import com.he.rating.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;


    @RequestMapping("/index")
    @AdminPermission(produceType = "text/html")
    public ModelAndView index(
            PageQuery pageQuery
    ) {
        ModelAndView mav = new ModelAndView("admin/seller/index.html");

        PageHelper.startPage(
                pageQuery.getPage(), pageQuery.getSize()
        );
        List<SellerModel> sellerModels = sellerService.selectAll();
        PageInfo<SellerModel> sellerModelPageInfo = new PageInfo<>(sellerModels);

        mav.addObject("data", sellerModelPageInfo);
        mav.addObject("CONTROLLER_NAME", "seller");
        mav.addObject("ACTION_NAME", "index");
        return mav;
    }

    @RequestMapping("/create_page")
    @AdminPermission(produceType = "text/html")
    public ModelAndView createPage() {
        ModelAndView mav = new ModelAndView("admin/seller/create.html");

        mav.addObject("CONTROLLER_NAME", "seller");
        mav.addObject("ACTION_NAME", "create");
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission(produceType = "text/html")
    public String create(
            @Valid SellerCreateReq sellerCreateReq,
            BindingResult bindingResult
    ) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(
                    EmBussinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult)
            );
        }

        SellerModel sellerModel = new SellerModel();
        BeanUtils.copyProperties(sellerCreateReq, sellerModel);
        sellerService.create(sellerModel);

        return "redirect:/admin/seller/index";
    }

    @RequestMapping(value = "down", method = RequestMethod.POST)
    @AdminPermission(produceType = "text/html")
    @ResponseBody
    public CommonRes down(@RequestParam(value = "id") Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id, 1);
        return CommonRes.create(sellerModel);
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    @AdminPermission(produceType = "text/html")
    @ResponseBody
    public CommonRes up(@RequestParam(value = "id") Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id, 0);
        return CommonRes.create(sellerModel);
    }
}
