package nals.tuyen.test.service;


import nals.tuyen.test.define.Define;
import nals.tuyen.test.define.ValidateString;
import nals.tuyen.test.dto.ListDTO;
import nals.tuyen.test.dto.TaskDTO;
import nals.tuyen.test.entities.Task;
import nals.tuyen.test.exception.CustomException;
import nals.tuyen.test.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class TaskService {
  
  @Autowired
  TaskRepository taskRepository;

  @Autowired
  private ModelMapper modelMapper;

  public String addTask(Task task) {
    Date startDate;
    Date endDate;

    try {
      startDate = new SimpleDateFormat("yyyy-MM-dd").parse(task.getStartDate());
      endDate = new SimpleDateFormat("yyyy-MM-dd").parse(task.getEndDate());
    } catch (ParseException e) {
      throw new CustomException("Error data", 400);
    }
    if(startDate.after(endDate)){
      throw new CustomException("startDate can't greater than endDate! ", 400);
    }

    task.setNameTask(ValidateString.deleteSpace(task.getNameTask()));
    task.setContent(ValidateString.deleteSpace(task.getContent()));

    if(task.getNameTask().length() < 5){
      throw new CustomException("The name is too short!", 400);
    }
    if(task.getContent().length() < 5){
      throw new CustomException("The content is too short!", 400);
    }

    task.setDeleteFlag(0);
    taskRepository.save(task);
    return "add task success!";
  }


  public String editTask(int idTask, Task task) {
    //find task by id
    Task taskOld = taskRepository.findByIdTaskAndDeleteFlag(idTask, 0);
    if(taskOld == null){
      throw new CustomException("task not found!",404);
    }

    Date startDate;
    Date endDate;

    try {
      startDate = new SimpleDateFormat("yyyy-MM-dd").parse(task.getStartDate());
      endDate = new SimpleDateFormat("yyyy-MM-dd").parse(task.getEndDate());
    } catch (ParseException e) {
      throw new CustomException("Error data", 400);
    }
    if(startDate.after(endDate)){
      throw new CustomException("startDate can't greater than endDate! ", 400);
    }

    task.setNameTask(ValidateString.deleteSpace(task.getNameTask()));
    task.setContent(ValidateString.deleteSpace(task.getContent()));

    if(task.getNameTask().length() < 5){
      throw new CustomException("The name is too short!", 400);
    }
    if(task.getContent().length() < 5){
      throw new CustomException("The content is too short!", 400);
    }

    task.setDeleteFlag(0);
    task.setIdTask(idTask);
    taskRepository.save(task);
    return "edit task success!";
  }

  public String deleteTask(int idTask) {

    //find task by id
    Task taskDelete = taskRepository.findByIdTaskAndDeleteFlag(idTask, 0);

    if(taskDelete == null){
      throw new CustomException("task not found!",404);
    }
    taskDelete.setDeleteFlag(1);
    taskRepository.save(taskDelete);
    return "delete task success!";
  }

  public ListDTO listTaskCalendar(String startDate, String endDate, Optional<Integer> page, Optional<Integer> pageSize) {
    int evalPageSize = pageSize.orElse(Define.initialPageSize);
    int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

    int total = taskRepository.findTaskCalendar(startDate, endDate, 0);

    ArrayList<Object> result = new ArrayList<>();
    TaskDTO taskDTO = new TaskDTO();

    ArrayList<Task> taskCalendarList = taskRepository.findTaskCalendar(startDate, endDate, 0,PageRequest.of(evalPage, evalPageSize));

    for(Task objTask : taskCalendarList){
      taskDTO = modelMapper.map(objTask, taskDTO.getClass());
      result.add(taskDTO);
    }

    return new ListDTO(total, result);
  }

  public ListDTO listTask(Optional<Integer> page, Optional<Integer> pageSize) {

    int evalPageSize = pageSize.orElse(Define.initialPageSize);
    int evalPage = (page.orElse(0) < 1) ? Define.initialPage : page.get() - 1;

    ArrayList<Task> taskList = taskRepository.findByDeleteFlag(0, PageRequest.of(evalPage, evalPageSize));
    int total = taskRepository.countByDeleteFlag(0);

    ArrayList<Object> result = new ArrayList<>();
    TaskDTO taskDTO = new TaskDTO();

    for(Task objTask : taskList){
      taskDTO = modelMapper.map(objTask, taskDTO.getClass());
      result.add(taskDTO);
    }

    return new ListDTO(total, result);
  }
}
