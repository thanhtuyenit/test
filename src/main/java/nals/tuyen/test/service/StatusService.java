package nals.tuyen.test.service;


import nals.tuyen.test.dto.ListDTO;
import nals.tuyen.test.dto.TaskDTO;
import nals.tuyen.test.entities.Status;
import nals.tuyen.test.entities.Task;
import nals.tuyen.test.exception.CustomException;
import nals.tuyen.test.repository.StatusRepository;
import nals.tuyen.test.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StatusService {
  
  @Autowired
  StatusRepository statusRepository;


  public List<Status> listStatus() {
    return statusRepository.findByDeleteFlag(0);
  }
}
