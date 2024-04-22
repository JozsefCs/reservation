package hu.cj.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Reservation {
    private long id;
    @NotEmpty(message = "Name cannot be empty")
    private String reservationName;
    @Pattern(message = "Phone number is not valid", regexp = "(^$|[0-9]{11})")
    @NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;
//    @Email(message = "Email is not valid")
    @Pattern(message="Please provide a valid email address",regexp = "[A-Za-z0-9\\._%+\\-]+@[A-Za-z0-9\\.\\-]+\\.[A-Za-z]{2,}")
    @NotEmpty(message = "Email cannot be empty")
    private String eMail;

    @NotEmpty(message = "Start time cannot be empty")
//    @Schema(type = "string")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @NotEmpty(message = "End time cannot be empty")
//    @Schema(type = "string")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    @NotNull(message = "Service id cannot be empty")
    private int serviceId;
    public Reservation(long id, String reservationName, String phoneNumber, String eMail, LocalDateTime startTime, LocalDateTime endTime, int serviceId) {
        this.id = id;
        this.reservationName = reservationName;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceId = serviceId;
    }

    public Reservation(String reservationName, String phoneNumber, String eMail, LocalDateTime startTime, LocalDateTime endTime, int serviceId) {
        this.reservationName = reservationName;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Reservation() {
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

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
