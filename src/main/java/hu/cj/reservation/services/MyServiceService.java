package hu.cj.reservation.services;

import hu.cj.reservation.dto.MyService;
import hu.cj.reservation.entity.MyServiceEntity;
import hu.cj.reservation.repository.ServiceDataJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyServiceService {
    ServiceDataJpaRepository serviceDataJpaRepository;

    public MyServiceService(ServiceDataJpaRepository serviceDataJpaRepository) {
        this.serviceDataJpaRepository = serviceDataJpaRepository;
    }

    public List<MyService> getAllService() {
        List<MyService> result = new ArrayList<>();
        List<MyServiceEntity> all = serviceDataJpaRepository.findAll();
        all.stream().forEach(e -> {
            result.add(convertMyServiceEntityToDao(e));
        });
        return result;
    }

    public List<MyService> getAllServiceWithoutDetails() {
        List<MyService> result = new ArrayList<>();
        List<MyServiceEntity> all = serviceDataJpaRepository.getAllService();
        all.stream().forEach(e -> {
            result.add(convertMyServiceEntityToDao(e));
        });
        return result;
    }

    public MyServiceEntity findById(long id) {
        Optional<MyServiceEntity> servicesbyid = serviceDataJpaRepository.findById(id);
        if (servicesbyid.isPresent()) {
            return servicesbyid.get();
        }
        return null;
    }

    public MyService findByIdWithoutDetails(long id) {
        MyServiceEntity servicesbyid = serviceDataJpaRepository.getServiceById(id);
        return convertMyServiceEntityToDao(servicesbyid);
    }

    public MyService saveService(MyServiceEntity myServiceEntity) {
        return convertMyServiceEntityToDao(serviceDataJpaRepository.save(myServiceEntity));
    }

    public void deleteService(long id) {
        Optional<MyServiceEntity> byId = serviceDataJpaRepository.findById(id);
        if (byId.isPresent() && serviceCanBeDelete(byId.get()))
            serviceDataJpaRepository.deleteById(id);
    }

    public boolean serviceCanBeDelete(MyServiceEntity service) {
        if(service.getReservation() == null || service.getReservation().isEmpty())
            return true;
        return false;
    }

    public MyService convertMyServiceEntityToDao(MyServiceEntity e) {
        MyService myService = new MyService();
        myService.setId(e.getId());
        myService.setServiceName(e.getServiceName());
        myService.setDescription(e.getDescription());
        myService.setPeriod(e.getPeriod());
        if(e.getCompany()!= null)
            myService.setCompanyId(e.getCompany().getId());
//        return new MyService(e.getId(),e.getServiceName(),e.getDescription(),e.getPeriod(),e.getCompany()== null? null:e.getCompany().getId());
        return myService;
    }
}
