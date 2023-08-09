package com.week8.Activity.Tracker.dto.response;

import com.week8.Activity.Tracker.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {
    private Long id;
    private  String title;
    private String description;
    private TaskStatus taskStatus;
    private LocalDateTime createdAt;
}

