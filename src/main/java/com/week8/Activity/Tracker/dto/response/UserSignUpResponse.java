package com.week8.Activity.Tracker.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignUpResponse {
    private String userName;
    private String email;

}
