package hu.cj.reservation.controller;

import hu.cj.reservation.dto.Company;
import hu.cj.reservation.entity.CompanyEntity;
import hu.cj.reservation.services.CompanyService;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService CompanyService) {
        this.companyService = CompanyService;
    }

    @GetMapping(path = "/company/getAllCompany")
    public List<Company> getAllCompany() {
        return companyService.getAllCompany();
    }


    @GetMapping(path = "/company/getCompanyById/{id}")
    public Company getCompanyById(@PathVariable long id) {
        return companyService.convertCompanyEntityToDao(companyService.findById(id));
    }


    @PostMapping(path = "/company/createNewCompany")
    @SecurityRequirement(name = "Bearer Authentication")
    public Company createNewCompany(@Valid @ParameterObject Company company) {
        try {
            CompanyEntity entity = new CompanyEntity(company.getCompanyName(), company.getAddress(), company.getOpenTime(), company.getCloseTime());
            Company savedCompanyEntity = companyService.saveCompany(entity);
            return savedCompanyEntity;
        } catch (Exception ex) {
            return null;
        }
    }

    @PostMapping(path = "/company/modifyCompany")
    @SecurityRequirement(name = "Bearer Authentication")
    public Company modifyCompany(@Valid @ParameterObject Company company) {
        try {
            CompanyEntity byId = companyService.findById(company.getId());

            byId.setCompanyName(company.getCompanyName());
            byId.setAddress(company.getAddress());
            byId.setOpenTime(company.getOpenTime());
            byId.setCloseTime(company.getCloseTime());

            Company updatedCompanyEntity = companyService.saveCompany(byId);
            return updatedCompanyEntity;
        } catch (Exception ex) {
            return null;
        }
    }

    @DeleteMapping(path = "/company/deleteCompany/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public void deleteCompany(@PathVariable long id) {
        companyService.deleteCompany(id);
    }
}

