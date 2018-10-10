package nals.tuyen.test.controller;


import nals.tuyen.test.dto.APIResponseDTO;
import nals.tuyen.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ListTaskCalendarController {
  @Autowired
  TaskService taskService;

  @RequestMapping(value = "/task/calendar", params = {"startDate", "endDate", "page", "pageSize"}, method = RequestMethod.GET)
  public APIResponseDTO listTask(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
                                 @RequestParam("page") Optional<Integer> page, @RequestParam("pageSize") Optional<Integer> pageSize) {
    return new APIResponseDTO(200, "Success!", taskService.listTaskCalendar(startDate, endDate, page, pageSize));
  }
}
