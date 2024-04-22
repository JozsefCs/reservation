package hu.cj.reservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "SERVICE")
public class MyServiceEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name cannot be empty")
    private String serviceName;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @NotNull(message = "Period cannot be empty")
    private int period;
    @ManyToOne(cascade = CascadeType.DETACH)
    private CompanyEntity companyEntity;
    @OneToMany(mappedBy = "myServiceEntity")
    @JsonIgnore
    private List<ReservationEntity> reservationEntity;

    public MyServiceEntity(int id, String serviceName, String description, int period, CompanyEntity companyEntity, List<ReservationEntity> reservationEntity) {
        this.id = id;
        this.serviceName = serviceName;
        this.description = description;
        this.period = period;
        this.companyEntity = companyEntity;
        this.reservationEntity = reservationEntity;
    }

    public MyServiceEntity(int id,String serviceName, String description, int period) {
        this.id = id;
        this.serviceName = serviceName;
        this.description = description;
        this.period = period;
    }

    public MyServiceEntity(int id) {
        this.id = id;
    }

    public MyServiceEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public CompanyEntity getCompany() {
        return companyEntity;
    }

    public void setCompany(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public List<ReservationEntity> getReservation() {
        return reservationEntity;
    }

    public void setReservation(List<ReservationEntity> reservationEntity) {
        this.reservationEntity = reservationEntity;
    }

    @Override
    public String toString() {
        return "MyServiceEntity{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", description='" + description + '\'' +
                ", period=" + period +
                ", companyEntity=" + companyEntity +
                ", reservationEntity=" + reservationEntity +
                '}';
    }
}
