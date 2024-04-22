package hu.cj.reservation.services;

import hu.cj.reservation.dto.FreeDate;
import hu.cj.reservation.dto.MyService;
import hu.cj.reservation.dto.Reservation;
import hu.cj.reservation.entity.CompanyEntity;
import hu.cj.reservation.entity.MyServiceEntity;
import hu.cj.reservation.entity.ReservationEntity;
import hu.cj.reservation.repository.CompanyDataJpaRepository;
import hu.cj.reservation.repository.ReservationDataJpaRepository;
import hu.cj.reservation.repository.ServiceDataJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private ReservationDataJpaRepository reservationDataJpaRepository;
    private MyServiceService myServiceService;

    private ServiceDataJpaRepository serviceDataJpaRepository;
    private CompanyDataJpaRepository companyDataJpaRepository;

    public ReservationService(ReservationDataJpaRepository reservationDataJpaRepository,MyServiceService myServiceService,CompanyDataJpaRepository companyDataJpaRepository,ServiceDataJpaRepository serviceDataJpaRepository) {
        this.reservationDataJpaRepository = reservationDataJpaRepository;
        this.myServiceService = myServiceService;
        this.companyDataJpaRepository = companyDataJpaRepository;
        this.serviceDataJpaRepository = serviceDataJpaRepository;
    }

    public List<Reservation> getReservationsByServiceAndDates(long serviceId, LocalDateTime startTime, LocalDateTime endTime) {
        List<ReservationEntity> reservationsByCompanyAndDates = reservationDataJpaRepository.getReservationsByServiceAndDates(serviceId, startTime, endTime);
        List<Reservation> result = new ArrayList<>();
        reservationsByCompanyAndDates.stream().forEach(e -> {
            result.add(convertToReservationEntityToDao(e));
        });
        return result;
    }



public List<FreeDate> getFreeDatesByServiceAndDates(long serviceId, LocalDateTime startTime, LocalDateTime endTime) {
    Optional<MyServiceEntity> byId = serviceDataJpaRepository.findById(serviceId);
    if(byId.isPresent()){
        long companyId = byId.get().getCompany().getId();
        MyService serviceById = myServiceService.findByIdWithoutDetails(serviceId);
        CompanyEntity companyEntity = companyDataJpaRepository.getCompanyById(companyId);

        companyEntity.setId((int)companyId);
        List<FreeDate> freeDates = findFreeDates(startTime,endTime, serviceById.getPeriod(), companyEntity);

        return freeDates;
    }
    return  null;


}

    public List<Reservation> getReservationsByCompanyAndDates(long companyId, LocalDateTime startTime, LocalDateTime endTime) {
        List<ReservationEntity> reservationsByCompanyAndDates = reservationDataJpaRepository.getReservationsByCompanyAndDates(companyId, startTime, endTime);
        List<Reservation> result = new ArrayList<>();
        reservationsByCompanyAndDates.stream().forEach(e->{
            result.add(convertToReservationEntityToDao(e));
        });
        return result;
    }

    private List<FreeDate> findFreeDates(LocalDateTime startTime, LocalDateTime endTime, int period, CompanyEntity companyEntity) {

        List<FreeDate> result = new ArrayList<>();
        LocalDateTime start = startTime;

        while(start.isBefore(endTime)){
            if(start.toLocalTime().isBefore(companyEntity.getOpenTime())){
                start = LocalDateTime.of(start.toLocalDate(), companyEntity.getOpenTime());
            }else if(start.toLocalTime().isAfter(companyEntity.getCloseTime())){
                start = LocalDateTime.of(start.toLocalDate().plusDays(1l), companyEntity.getOpenTime());
            }
            LocalDateTime end = start.plusMinutes((long) (period));
            List<ReservationEntity> reservationsByCompanyAndDates = reservationDataJpaRepository.getReservationsByCompanyAndDates(companyEntity.getId(), start, end);
            if(reservationsByCompanyAndDates == null || reservationsByCompanyAndDates.isEmpty() && end.toLocalTime().isBefore(companyEntity.getCloseTime())){
                result.add(new FreeDate(start,end));
            }
            start = start.plusMinutes(30l);
        }
        return result;
    }

    public Reservation saveNewReservation(ReservationEntity reservation) {
        MyServiceEntity serviceById = myServiceService.findById(reservation.getMyService().getId());
        CompanyEntity companyById = companyDataJpaRepository.getCompanyById(serviceById.getCompany().getId());
        if(!(findFreeDates(reservation.getStartTime(), reservation.getEndTime(), reservation.getMyService().getPeriod(), companyById).isEmpty())){
            return convertToReservationEntityToDao(reservationDataJpaRepository.save(reservation));
        }
        return null;
    }

    private Reservation convertToReservationEntityToDao(ReservationEntity e) {
        Reservation res = new Reservation();
        res.setId(e.getId());
        res.setReservationName(e.getReservationName());
        res.setPhoneNumber(e.getPhoneNumber());
        res.seteMail(e.geteMail());
        res.setStartTime(e.getStartTime());
        res.setEndTime(e.getEndTime());
        res.setServiceId(e.getMyService().getId());
        return  res;
    }

    public void deleteReservation(long id) {
        ReservationEntity reservationById = reservationDataJpaRepository.getReservationById(id);
        reservationDataJpaRepository.delete(reservationById);
    }
}
