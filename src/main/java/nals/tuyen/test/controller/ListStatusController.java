package nals.tuyen.test.controller;


import nals.tuyen.test.dto.APIResponseDTO;
import nals.tuyen.test.service.StatusService;
import nals.tuyen.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ListStatusController {
  @Autowired
  StatusService statusService;

  @RequestMapping(value = "/status", method = RequestMethod.GET)
  public APIResponseDTO listStatus() {
    return new APIResponseDTO(200, "Success!", statusService.listStatus());
  }
}
