package com.he.rating.controller.admin;

import com.he.rating.common.BusinessException;
import com.he.rating.common.EmBussinessError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
@RequestMapping(value = "admin/admin")
public class AdminController {

    @Value("${admin.email}")
    private String email;

    @Value("${admin.encryptPassword}")
    private String encryptPassword;

    @Autowired
    private HttpServletRequest httpServletRequest;

    public static final String CURRENT_ADMIN_SESSION = "currentAdminSession";

    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/admin/admin/index.html");
        return mav;
    }

    @GetMapping(value = "/login_page")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("/admin/admin/login.html");
        return mav;
    }

    @PostMapping(value = "/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) throws BusinessException, NoSuchAlgorithmException {
        if (!StringUtils.hasLength(email) || !StringUtils.hasLength(password)) {
            throw new BusinessException(
                    EmBussinessError.PARAMETER_VALIDATION_ERROR,
                    "email or password is empty"
            );
        }

        if (
                email.equals(this.email) &&
                        encodeByMd5(password).equals(this.encryptPassword)
        ) {
            // success
            httpServletRequest.getSession().setAttribute(CURRENT_ADMIN_SESSION, email);
            return "redirect:/admin/admin/index";
        }

        throw new BusinessException(
                EmBussinessError.PARAMETER_VALIDATION_ERROR,
                "email or password is incorrect"
        );
    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hash = digest.digest(
                str.getBytes(StandardCharsets.UTF_8)
        );
        return Base64.getEncoder().encodeToString(hash);
    }
}
