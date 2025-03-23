package com.he.rating.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CommonUtil {

    public static String processErrorString(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage()).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
