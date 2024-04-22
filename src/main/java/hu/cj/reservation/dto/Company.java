package hu.cj.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalTime;

public class Company {
    private int id;

    @NotEmpty(message = "Name cannot be empty")
    private String companyName;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Open time cannot be empty")
    @Schema(type = "string")
    @JsonFormat(pattern = "HH:mm:SS")
    private LocalTime openTime;

    @NotEmpty(message = "Close time cannot be empty")
    @Schema(type = "string")
    @JsonFormat(pattern = "HH:mm:SS")
    private LocalTime closeTime;

    public Company(int id, String companyName, String address, LocalTime openTime, LocalTime closeTime) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public Company(String companyName, String address, LocalTime openTime, LocalTime closeTime) {
        this.companyName = companyName;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }
}
