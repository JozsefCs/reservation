package hu.cj.reservation.services;

import hu.cj.reservation.dto.Company;
import hu.cj.reservation.entity.CompanyEntity;
import hu.cj.reservation.entity.MyServiceEntity;
import hu.cj.reservation.repository.CompanyDataJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private CompanyDataJpaRepository companyDataJpaRepository;
    private MyServiceService myServiceService;

    public CompanyService(CompanyDataJpaRepository companyDataJpaRepository, MyServiceService myServiceService) {
        this.companyDataJpaRepository = companyDataJpaRepository;
        this.myServiceService = myServiceService;
    }

    public List<Company> getAllCompany() {

        List<Company> result = new ArrayList<>();
        List<CompanyEntity> all = companyDataJpaRepository.findAll();

        all.stream().forEach(e ->{
            result.add(convertCompanyEntityToDao(e));
        });

        return result;
    }

    public List<Company> getAllCompanyWithoutDetails() {
        List<CompanyEntity> allCompanyEntity = companyDataJpaRepository.getAllCompany();
        List<Company> result = new ArrayList<>();
        allCompanyEntity.stream().forEach(e -> {
            result.add(convertCompanyEntityToDao(e));
        });
        return result;
    }

    public CompanyEntity findById(long id) {
        Optional<CompanyEntity> companysById = companyDataJpaRepository.findById(id);
        if (companysById.isPresent()) {
            return companysById.get();
        }
        return null;
    }

    public Company findByIdWithoutDetails(long id) {
        CompanyEntity companyEntityById = companyDataJpaRepository.getCompanyById(id);
        return convertCompanyEntityToDao(companyEntityById);
    }

    public Company saveCompany(CompanyEntity companyEntity) {
        return convertCompanyEntityToDao(companyDataJpaRepository.save(companyEntity));
    }

    public void deleteCompany(long id) {
        CompanyEntity companyEntity = findById(id);
        if (companyEntity != null && companyCanBeDelete(companyEntity))
            companyDataJpaRepository.deleteById(id);
    }

    private boolean companyCanBeDelete(CompanyEntity companyEntity) {
        if ((companyEntity.getServices() == null && companyEntity.getServices().isEmpty()
                && checkServicesForDelete(companyEntity.getServices()))) ;
        return true;
    }

    private boolean checkServicesForDelete(List<MyServiceEntity> services) {
        boolean canDelete = true;
        for (MyServiceEntity service : services) {
            if (!myServiceService.serviceCanBeDelete(service)) {
                canDelete = false;
                break;
            }

        }
        return canDelete;
    }

    public Company convertCompanyEntityToDao(CompanyEntity e) {
        Company company = new Company(e.getId(),e.getCompanyName(),e.getAddress(),e.getOpenTime(),e.getCloseTime());
        return company;
    }
}
