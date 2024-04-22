package hu.cj.reservation.controller;

import hu.cj.reservation.dto.FreeDate;
import hu.cj.reservation.dto.Reservation;
import hu.cj.reservation.entity.MyServiceEntity;
import hu.cj.reservation.entity.ReservationEntity;
import hu.cj.reservation.services.ReservationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(path = "/reservation/getReservationsByServiceAndDates")
    public List<Reservation> getReservationsByServiceAndDates(@RequestParam long id, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime endTime) {
        return reservationService.getReservationsByServiceAndDates(id,startTime,endTime);
    }

    @GetMapping(path = "/reservation/getReservationsByCompanyAndDates")
    public List<Reservation> getReservationsByCompanyAndDates(@RequestParam long id, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime endTime) {
        return reservationService.getReservationsByCompanyAndDates(id,startTime,endTime);
    }


    @GetMapping(path = "/reservation/getFreeDatesByServiceAndDates")
    public List<FreeDate> getFreeDatesByCompanyAndDates(@RequestParam long serviceId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime endTime) {
        return reservationService.getFreeDatesByServiceAndDates(serviceId,startTime,endTime);
    }

    @PostMapping(path = "/reservation/createNewReservation")
    @SecurityRequirement(name = "Bearer Authentication")
    public Reservation createNewReservation(@Valid @ParameterObject Reservation reservation){

        ReservationEntity res = new ReservationEntity();
        res.setReservationName(reservation.getReservationName());
        res.setPhoneNumber(reservation.getPhoneNumber());
        res.seteMail(reservation.geteMail());
        res.setStartTime(reservation.getStartTime());
        res.setEndTime(reservation.getEndTime());
        res.setMyService(new MyServiceEntity(reservation.getServiceId()));

        return reservationService.saveNewReservation(res);
    }

    @DeleteMapping(path = "/reservation/deleteReservation/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public void deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
    }


}
