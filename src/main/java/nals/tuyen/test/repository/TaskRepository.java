package nals.tuyen.test.repository;

import nals.tuyen.test.entities.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
  ArrayList<Task> findByDeleteFlag(int deleteFlag, Pageable pageable);

  Task findByIdTaskAndDeleteFlag(int idTask, int deleteFlag);

  int countByDeleteFlag(int deleteFlag);

  @Query(value = "SELECT * FROM task\n" +
          "WHERE delete_flag = ?3\n" +
          "AND ((?1 BETWEEN  start_date AND end_date)\n" +
          "OR (?2 BETWEEN  start_date AND end_date))", nativeQuery = true)
  ArrayList<Task> findTaskCalendar(String startDate, String endDate, int deleteFlag, Pageable pageable);

  @Query(value = "SELECT COUNT(*) FROM task\n" +
          "WHERE delete_flag = ?3\n" +
          "AND ((?1 BETWEEN  start_date AND end_date)\n" +
          "OR (?2 BETWEEN  start_date AND end_date))", nativeQuery = true)
   int findTaskCalendar(String startDate, String endDate, int deleteFlag);

//  ArrayList<Task> findByStartDateGreaterThanEqualOrEndDateLessThanEqualAndDeleteFlag(String startDate, String endDate,
//                                                                                     int deleteFlag, Pageable pageable);
//
//  int countByStartDateGreaterThanEqualOrEndDateLessThanEqualAndDeleteFlag(String startDate, String endDate, int deleteFlag);
}
