package com.week8.Activity.Tracker.controller;

import com.week8.Activity.Tracker.dto.request.UserLoginRequest;
import com.week8.Activity.Tracker.dto.request.UserSignUpRequest;
import com.week8.Activity.Tracker.dto.response.UserLoginResponse;
import com.week8.Activity.Tracker.services.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api")
public class AppUserController {
    public final AppUserService appUserService;

    @PostMapping(path = "/sign-up")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserSignUpRequest request) {
        var response = appUserService.signUp(request);
       return ResponseEntity.ok(response);

    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login( @RequestBody UserLoginRequest userLoginRequest,
                                                              HttpServletRequest request){
       // UserLoginResponse userLoginResponse = appUserService.login(userLoginRequest ,request);
        var UserLoginResponse = appUserService.login(userLoginRequest, request);
        return ResponseEntity.ok(UserLoginResponse);
    }


}