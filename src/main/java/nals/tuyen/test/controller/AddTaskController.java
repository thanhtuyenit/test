package nals.tuyen.test.controller;


import io.swagger.annotations.ApiParam;
import nals.tuyen.test.dto.APIResponseDTO;
import nals.tuyen.test.entities.Task;
import nals.tuyen.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AddTaskController {
  @Autowired
  TaskService taskService;

  @RequestMapping(value = "/task/add", method = RequestMethod.POST)
  public APIResponseDTO addTask(@ApiParam @RequestBody Task task) {
    return new APIResponseDTO(200, "Success!", taskService.addTask(task));
  }
}
