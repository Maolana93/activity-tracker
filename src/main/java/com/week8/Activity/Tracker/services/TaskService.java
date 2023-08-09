package com.week8.Activity.Tracker.services;

import com.week8.Activity.Tracker.dto.request.TaskRequest;
import com.week8.Activity.Tracker.dto.response.TaskDeleteResponse;
import com.week8.Activity.Tracker.dto.response.TaskEditedResponse;
import com.week8.Activity.Tracker.dto.response.TaskResponse;
import com.week8.Activity.Tracker.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    TaskResponse createTask (TaskRequest request, Long appUserId);

    TaskEditedResponse editTask (TaskRequest request, Long id, Long appUserId);

    TaskDeleteResponse deleteTask ( Long taskId, Long appUserId);

    TaskResponse viewATask (Long taskId,Long appUseId);

    TaskResponse moveTaskStatus (TaskStatus taskStatus, Long taskId, Long appUserId);

    List<TaskResponse> viewAllTask (Long request);

    List <TaskResponse> viewAllPendingTask (TaskRequest request);

    List <TaskResponse> viewAllDoneTask (TaskRequest request);

    List <TaskResponse> viewAllInProgressTask (TaskRequest request);
}
