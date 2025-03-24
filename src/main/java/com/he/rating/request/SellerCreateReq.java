package com.he.rating.request;

import jakarta.validation.constraints.NotBlank;

public class SellerCreateReq {

    @NotBlank(message = "name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
