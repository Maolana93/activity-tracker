package com.week8.Activity.Tracker.services.serviceImplementation;

import com.week8.Activity.Tracker.dto.request.UserLoginRequest;
import com.week8.Activity.Tracker.dto.request.UserSignUpRequest;
import com.week8.Activity.Tracker.dto.response.UserLoginResponse;
import com.week8.Activity.Tracker.dto.response.UserSignUpResponse;
import com.week8.Activity.Tracker.entity.AppUser;
import com.week8.Activity.Tracker.exception.AppUserException;
import com.week8.Activity.Tracker.exception.ResourceNotFoundException;
import com.week8.Activity.Tracker.repository.AppUserRepository;
import com.week8.Activity.Tracker.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppUserServiceImplementation implements AppUserService{
    private final AppUserRepository appUserRepository;

    @Override
    public UserSignUpResponse signUp(UserSignUpRequest request) {
        Optional<AppUser> appUser= appUserRepository.findByEmail(request.getEmail());
        if(appUser.isPresent()) {
            throw new AppUserException("user already exist");
        }

        AppUser newAppUser = AppUser.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        AppUser saveAppUser =appUserRepository.save(newAppUser);
        return UserSignUpResponse.builder()
                .email(saveAppUser.getEmail())
                .userName(saveAppUser.getUserName())
                .build();
    }
    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest, HttpServletRequest request) {
        Optional<AppUser> optionalAppUser= appUserRepository.findByEmail(userLoginRequest.getEmail());
        if (optionalAppUser.isEmpty()) {
            throw new ResourceNotFoundException("invalid email and password");
        }
        AppUser appUser = optionalAppUser.get();
               if(!userLoginRequest.getPassword().equals(optionalAppUser.get().getPassword())) {
            throw new ResourceNotFoundException("incorrect email and password");
        }

        HttpSession session= request.getSession();
               session.setAttribute("appUser", appUser);

        return UserLoginResponse.builder()
                .email(appUser.getEmail())
                .id(appUser.getId())
                .build();
    }



    }

