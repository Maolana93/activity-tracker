package com.week8.Activity.Tracker.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
@Builder
public class UserSignUpRequest {
    @NotBlank(message = "Kindly supply your email")
    private  String userName;

    @Email(message = "kindly provide you valid email")
    @NotBlank(message = "email is required")
    private  String email;
    private  String password;
}


