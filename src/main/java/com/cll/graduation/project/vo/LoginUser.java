package com.cll.graduation.project.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class LoginUser {

    private String name;

    private String email;

    private String token;

}
