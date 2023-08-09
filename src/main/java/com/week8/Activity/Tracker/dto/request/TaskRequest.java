package com.week8.Activity.Tracker.dto.request;

import com.week8.Activity.Tracker.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {
    private  String title;
    private String description;

}
