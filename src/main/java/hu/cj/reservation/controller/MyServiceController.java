package hu.cj.reservation.controller;

import hu.cj.reservation.dto.MyService;
import hu.cj.reservation.entity.CompanyEntity;
import hu.cj.reservation.entity.MyServiceEntity;
import hu.cj.reservation.services.MyServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MyServiceController {
    private MyServiceService myServiceService;

    public MyServiceController(MyServiceService myServiceService) {
        this.myServiceService = myServiceService;
    }

    @GetMapping(path = "/service/getAllService")
    public List<MyService> getAllService() {
        return myServiceService.getAllService();
    }

    @GetMapping(path = "/service/getServiceById/{id}")
    public MyService getServiceById(@PathVariable long id) {
        return myServiceService.convertMyServiceEntityToDao(myServiceService.findById(id));
    }

    @PostMapping(path = "/service/createNewService")
    @SecurityRequirement(name = "Bearer Authentication")
    public MyService createNewService(@Valid @ParameterObject MyService myService) {
        try {
            CompanyEntity companyEntity = new CompanyEntity();
            companyEntity.setId(myService.getCompanyId());

            MyServiceEntity myServiceEntity = new MyServiceEntity();
            myServiceEntity.setServiceName(myService.getServiceName());
            myServiceEntity.setDescription(myService.getDescription());
            myServiceEntity.setPeriod(myService.getPeriod());
            myServiceEntity.setCompany(companyEntity);

            MyService service = myServiceService.saveService(myServiceEntity);
            return service;
        } catch (Exception ex) {
            return null;
        }
    }

    @PostMapping(path = "/service/modifyService")
    @SecurityRequirement(name = "Bearer Authentication")
    public MyService modifyCompany(@Valid @ParameterObject MyService myService) {
        try {
            MyServiceEntity byId = myServiceService.findById(myService.getId());
            byId.setServiceName(myService.getServiceName());
            byId.setDescription(myService.getDescription());
            byId.setPeriod(myService.getPeriod());
            byId.setCompany(new CompanyEntity(myService.getCompanyId()));

            MyService updatedService = myServiceService.saveService(byId);
            return updatedService;
        } catch (Exception ex) {
            return null;
        }
    }

    @DeleteMapping(path = "/service/deleteService/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public void deleteService(@PathVariable long id) {
        myServiceService.deleteService(id);
    }
}
