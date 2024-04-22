package hu.cj.reservation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATION")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Name cannot be empty")
    private String reservationName;
    @Pattern(message = "Phone number is not valid", regexp = "(^$|[0-9]{11})")
    @NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;
    @Email(message = "Email is not valid", regexp = ".+@.+\\..+")
//    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
    @NotEmpty(message = "Email cannot be empty")
    private String eMail;

    @NotEmpty(message = "Start time cannot be empty")
    @Schema(type = "string")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @NotEmpty(message = "End time cannot be empty")
    @Schema(type = "string")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    @ManyToOne
    @JsonIgnore
    private MyServiceEntity myServiceEntity;

    public ReservationEntity(long id,String reservationName, String phoneNumber, String eMail, MyServiceEntity myServiceEntity, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.reservationName = reservationName;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.startTime = startTime;
        this.endTime = endTime;
        this.myServiceEntity = myServiceEntity;
    }

    public ReservationEntity(long id, String reservationName,String phoneNumber, String eMail,  LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.reservationName = reservationName;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ReservationEntity(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ReservationEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public MyServiceEntity getMyService() {
        return myServiceEntity;
    }

    public void setMyService(MyServiceEntity myServiceEntity) {
        this.myServiceEntity = myServiceEntity;
    }
}
