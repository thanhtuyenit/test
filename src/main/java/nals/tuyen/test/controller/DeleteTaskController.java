package nals.tuyen.test.controller;


import io.swagger.annotations.ApiParam;
import nals.tuyen.test.dto.APIResponseDTO;
import nals.tuyen.test.entities.Task;
import nals.tuyen.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteTaskController {
  @Autowired
  TaskService taskService;

  @RequestMapping(value = "/task/delete/{id}", method = RequestMethod.DELETE)
  public APIResponseDTO addTask(@PathVariable("id") int id) {
    return new APIResponseDTO(200, "Success!", taskService.deleteTask(id));
  }
}
