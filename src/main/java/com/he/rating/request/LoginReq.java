package com.he.rating.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginReq {

    @NotBlank(message = "telephone is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "telephone format invalid")
    private String telephone;

    @NotBlank(message = "password is required")
    @Size(min = 6, max = 20, message = "password length must be 6 to 20")
    private String password;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
