

package com.week8.Activity.Tracker.services.serviceImplementation;
import com.week8.Activity.Tracker.dto.request.TaskRequest;
import com.week8.Activity.Tracker.dto.response.TaskDeleteResponse;
import com.week8.Activity.Tracker.dto.response.TaskEditedResponse;
import com.week8.Activity.Tracker.dto.response.TaskResponse;
import com.week8.Activity.Tracker.entity.AppUser;
import com.week8.Activity.Tracker.entity.Task;
import com.week8.Activity.Tracker.entity.TaskStatus;
import com.week8.Activity.Tracker.exception.AppUserException;
import com.week8.Activity.Tracker.repository.AppUserRepository;
import com.week8.Activity.Tracker.repository.TaskRepository;
import com.week8.Activity.Tracker.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;
    @Override
    public TaskResponse createTask(TaskRequest request, Long appUserId) {
        Optional<AppUser> OptionalAppUser = appUserRepository.findById(appUserId);
        if (OptionalAppUser.isEmpty()){
            throw new AppUserException("User does not exist");
        }
        AppUser appUser = OptionalAppUser.get();
        //creating new Task
        Task newTask = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .appUser(appUser)
                .status(TaskStatus.PENDING)// default
                .build();
        Task saveTask = taskRepository.save(newTask);

        return  TaskResponse.builder()
                .id(saveTask.getId())
                .title(saveTask.getTitle())
                .description(saveTask.getDescription())
                .taskStatus(saveTask.getStatus())
                .createdAt(LocalDateTime.now())
                .build();

    }

    @Override
    public TaskEditedResponse editTask(TaskRequest request, Long taskId, Long appUserId) {
        Optional<AppUser> optionalAppUser= appUserRepository.findById(appUserId);
        if(optionalAppUser.isEmpty()){
            throw new AppUserException("User details could not be found");
        }else {
            AppUser appUser = optionalAppUser.get();
            //check if the task exist
            Optional<Task> optionalTask = taskRepository.findById(taskId);
            if(optionalTask.isEmpty()){
                throw new AppUserException("Your task was not found");
            }else {
                Task task = optionalTask.get();
                if(!task.getAppUser().getId().equals(appUser.getId())){
                    throw new AppUserException("User does not have the task");
                }
                Task editedTask = optionalTask.get();
                Task.builder()
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .editedAt(LocalDateTime.now())
                        .build();

                Task updatedTask = taskRepository.save(editedTask);

                return TaskEditedResponse.builder()
                        .id(updatedTask.getId())
                        .title(updatedTask.getTitle())
                        .description(updatedTask.getDescription())
                        .taskStatus(updatedTask.getStatus())
                        .editedAt(LocalDateTime.now())
                        .build();
            }
        }
    }

    @Override
    public TaskDeleteResponse deleteTask( Long taskId, Long appUserId) {
        Optional<AppUser> appUser =appUserRepository.findById(appUserId);
        if(appUser.isEmpty()) {
            throw new AppUserException("User details not found");
        }else {
            AppUser existingUser = appUser.get();
            Optional<Task> optionalTask= taskRepository.findById(taskId);
            if(optionalTask.isEmpty()){
                throw new AppUserException("Task not found");
            }else {
                Task existingTask = optionalTask.get();
                // check if the if id of user with AppUserId that created the task
                if(!existingTask.getAppUser().getId().equals(existingTask.getId())){
                    throw  new AppUserException("You are unable to delete task");
                }
                taskRepository.deleteById(taskId);
              return TaskDeleteResponse.builder()
                      .TaskId(taskId)
                      .message("Your Task has been deleted")
                      .build();
            }

        }
    }

    @Override
    public TaskResponse viewATask(Long taskId,Long appUserId) {
        Optional<AppUser> optionalAppUser =appUserRepository.findById(appUserId);
        if(optionalAppUser.isEmpty()){
            throw new AppUserException("User not found");
        }else{
            AppUser appUser=optionalAppUser.get();
            Optional<Task> tasks = taskRepository.findTaskByAppUserAndId(appUser,taskId);
            if(tasks.isEmpty()){
                throw new AppUserException("Task not found");
            }else {
                Task task = tasks.get();
                return TaskResponse.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .taskStatus(task.getStatus())
                        .createdAt(task.getCreatedAt())
                        .build();
            }
        }
    }

    @Override
    public TaskResponse moveTaskStatus(TaskStatus taskStatus, Long taskId, Long appUserId) {
        return null;
    }

    @Override
    public List<TaskResponse> viewAllTask(Long appUserId) {
        Optional<AppUser> optionalAppUser =appUserRepository.findById(appUserId);
        if(optionalAppUser.isEmpty()) {
            throw new AppUserException("User not found");
        }else {
            AppUser appUser = optionalAppUser.get();
            List<Task> task = taskRepository.findByAppUser(appUser);
            return  task.stream().map(editedtask -> TaskResponse.builder()
                    .id(editedtask.getId())
                    .title(editedtask.getTitle())
                    .description(editedtask.getDescription())
                    .taskStatus(editedtask.getStatus())
                    .createdAt(editedtask.getCreatedAt())
                    .build()).toList();

        }

    }

    @Override
    public List<TaskResponse> viewAllPendingTask(TaskRequest request) {
        return null;
    }

    @Override
    public List<TaskResponse> viewAllDoneTask(TaskRequest request) {
        return null;
    }

    @Override
    public List<TaskResponse> viewAllInProgressTask(TaskRequest request) {
        return null;
    }
}

