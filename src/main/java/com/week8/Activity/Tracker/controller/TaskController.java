package com.week8.Activity.Tracker.controller;

import com.week8.Activity.Tracker.dto.request.TaskRequest;
import com.week8.Activity.Tracker.dto.response.TaskResponse;
import com.week8.Activity.Tracker.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class TaskController {

    private  final TaskService taskService;

@PostMapping("createdTask/{Id}")
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskRequest request, @PathVariable("Id")Long Id){
        var response = taskService.createTask( request,Id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/editTask/{AppUserId}/{taskId}")
   public ResponseEntity<?> editedTask(@RequestBody @Valid TaskRequest request, @PathVariable("AppUserId") Long appUserId,
                                       @PathVariable("taskId") Long taskId){
    var response = taskService.editTask(request ,appUserId,taskId);
    return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteTask/{AppUserId}/{TaskId}")
    public ResponseEntity<?> deleteTask
            (@RequestBody @Valid @PathVariable("AppUserId")Long appUserId,@PathVariable("TaskId")Long taskId){
    var response = taskService.deleteTask(appUserId,taskId);
    return ResponseEntity.ok(response);
    }
    @GetMapping("/all/{AppUserId}")
    public ResponseEntity<List<TaskResponse>> viewAllTaskByUserId(@PathVariable("AppUserId") Long appUserId){
        List<TaskResponse> tasks = taskService.viewAllTask(appUserId);
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/task/{taskId}/{id}")
    public ResponseEntity<TaskResponse> viewATask(@PathVariable("taskId") Long taskId,@PathVariable("id")Long appUserId){
        TaskResponse taskResponse= taskService.viewATask(taskId,appUserId);
        return ResponseEntity.ok(taskResponse);
    }

}
