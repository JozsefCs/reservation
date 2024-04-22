package hu.cj.reservation.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MyService {

    private int id;
    @NotEmpty(message = "Name cannot be empty")
    private String serviceName;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @NotNull(message = "Period cannot be empty")
    private int period;
    @NotNull(message = "Company cannot be empty")
    private int companyId;

    public MyService(int id, String serviceName, String description, int period,int companyId) {
        this.id = id;
        this.serviceName = serviceName;
        this.description = description;
        this.period = period;
        this.companyId=companyId;
    }

    public MyService(String serviceName, String description, int period,int companyId) {
        this.serviceName = serviceName;
        this.description = description;
        this.period = period;
        this.companyId=companyId;
    }

    public MyService() {
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
