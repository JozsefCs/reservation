package hu.cj.reservation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "COMPANY")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "companyEntity")
    @JsonIgnore
    private List<MyServiceEntity> myServiceEntities;

    public CompanyEntity(int id, String companyName, String address, LocalTime openTime, LocalTime closeTime) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public CompanyEntity(String companyName, String address, LocalTime openTime, LocalTime closeTime) {
        this.companyName = companyName;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public CompanyEntity(int id, String companyName, String address, LocalTime openTime, LocalTime closeTime, List<MyServiceEntity> myServiceEntities) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.myServiceEntities = myServiceEntities;
    }

    public CompanyEntity(int id) {
        this.id = id;
    }

    public CompanyEntity() {
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

    public List<MyServiceEntity> getServices() {
        return myServiceEntities;
    }

    public void setServices(List<MyServiceEntity> myServiceEnitities) {
        this.myServiceEntities = myServiceEnitities;
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", myServiceEnitities=" + myServiceEntities +
                '}';
    }
}
