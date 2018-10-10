package nals.tuyen.test.repository;

import nals.tuyen.test.entities.Status;
import nals.tuyen.test.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
  ArrayList<Status> findByDeleteFlag(int deleteFlag);
}
