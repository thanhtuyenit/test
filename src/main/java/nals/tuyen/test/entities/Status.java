package nals.tuyen.test.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "status")
public class Status implements Serializable {

  @Id
  @SequenceGenerator(name = "seq", sequenceName = "oracle_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
  @Column(name = "id")
  private int idStatus;

  @Column(name = "name")
  private String nameStatus;

  @JsonIgnore
  @Column(name = "delete_flag")
  private int deleteFlag;


  public int getIdStatus() {
    return idStatus;
  }

  public void setIdStatus(int idStatus) {
    this.idStatus = idStatus;
  }

  public String getNameStatus() {
    return nameStatus;
  }

  public void setNameStatus(String nameStatus) {
    this.nameStatus = nameStatus;
  }

  public int getDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(int deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
}
