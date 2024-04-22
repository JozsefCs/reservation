package hu.cj.reservation.repository;

import hu.cj.reservation.entity.MyServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceDataJpaRepository extends JpaRepository<MyServiceEntity,Long> {
    @Query("select new MyServiceEntity(s.id,s.serviceName, s.description, s.period) from MyServiceEntity s where s.id = :serviceId")
    MyServiceEntity getServiceById(@Param("serviceId") long id);

    @Query("select new MyServiceEntity(s.id,s.serviceName, s.description, s.period) from MyServiceEntity s")
    List<MyServiceEntity> getAllService();
}
