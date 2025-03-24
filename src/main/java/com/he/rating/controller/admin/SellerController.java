package com.he.rating.controller.admin;

import com.he.rating.aspect.AdminPermission;
import com.he.rating.model.SellerModel;
import com.he.rating.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;


    @RequestMapping("/index")
    @AdminPermission(produceType = "text/html")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("admin/seller/index.html");

        List<SellerModel> sellerModels = sellerService.selectAll();
        mav.addObject("data", sellerModels);
        mav.addObject("CONTROLLER_NAME", "seller");
        mav.addObject("ACTION_NAME", "index");
        return mav;
    }
}
