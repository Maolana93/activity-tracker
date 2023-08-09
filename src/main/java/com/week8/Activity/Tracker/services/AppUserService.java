package com.week8.Activity.Tracker.services;

import com.week8.Activity.Tracker.dto.request.UserLoginRequest;
import com.week8.Activity.Tracker.dto.request.UserSignUpRequest;
import com.week8.Activity.Tracker.dto.response.UserLoginResponse;
import com.week8.Activity.Tracker.dto.response.UserSignUpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface AppUserService {
   // UserLoginResponse loginUser(UserLoginRequest userLoginRequest, HttpServletRequest request);

    UserSignUpResponse signUp(UserSignUpRequest request);

    UserLoginResponse login(UserLoginRequest userLoginRequest, HttpServletRequest request);
}

