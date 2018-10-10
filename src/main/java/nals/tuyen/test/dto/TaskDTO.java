package nals.tuyen.test.dto;

import nals.tuyen.test.entities.Status;

public class TaskDTO {
  private int idTask;

  private String nameTask;

  private String content;

  private String startDate;

  private String endDate;

  private Status status;

  public TaskDTO() {
  }

  public TaskDTO(int idTask, String nameTask, String content, String startDate, String endDate, Status status) {
    this.idTask = idTask;
    this.nameTask = nameTask;
    this.content = content;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
  }

  public int getIdTask() {
    return idTask;
  }

  public void setIdTask(int idTask) {
    this.idTask = idTask;
  }

  public String getNameTask() {
    return nameTask;
  }

  public void setNameTask(String nameTask) {
    this.nameTask = nameTask;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
