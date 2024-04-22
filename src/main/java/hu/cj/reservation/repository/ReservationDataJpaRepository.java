package hu.cj.reservation.repository;

import hu.cj.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationDataJpaRepository extends JpaRepository<ReservationEntity, Long> {
    @Query("select new ReservationEntity(r.startTime,r.endTime) from ReservationEntity r, MyServiceEntity s where r.myServiceEntity.id = s.id and s.id = :serviceId and r.startTime >= :startTime or r.endTime <= :endTime order by r.startTime desc ")
    List<ReservationEntity> getReservationsByServiceAndDates(@Param("serviceId") long serviceId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

        @Query("select new ReservationEntity(r.startTime,r.endTime) from ReservationEntity r, CompanyEntity c where r.myServiceEntity.companyEntity.id = c.id and c.id = :companyId and (r.startTime between :startTime and :endTime) or (r.endTime between :startTime and :endTime)")
    List<ReservationEntity> getReservationsByCompanyAndDates(@Param("companyId") long companyId, @Param("startTime")LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query("select new ReservationEntity(r.id, r.reservationName,r.phoneNumber,r.eMail,r.startTime,r.endTime) from  ReservationEntity r where r.id = :resId")
    ReservationEntity getReservationById(@Param("resId") long resId);
}
