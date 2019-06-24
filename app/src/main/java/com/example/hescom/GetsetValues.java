package com.example.hescom;

import java.io.Serializable;

public class GetsetValues implements Serializable {
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role="";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String code="";
    private String name="";
    public String getMonth_flag() {
        return month_flag;
    }

    public void setMonth_flag(String month_flag) {
        this.month_flag = month_flag;
    }

    private String month_flag="";

}
