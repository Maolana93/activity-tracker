package com.week8.Activity.Tracker.dto.response;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class UserLoginResponse {
    private String email;
    private Long id;
}
