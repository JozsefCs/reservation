package hu.cj.reservation.repository;

import hu.cj.reservation.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyDataJpaRepository extends JpaRepository<CompanyEntity,Long> {
    @Query("select new CompanyEntity(c.id, c.companyName, c.address, c.openTime,c.closeTime) from CompanyEntity c where c.id = :companyId")
    CompanyEntity getCompanyById(@Param("companyId") long companyId);

    @Query("select new CompanyEntity(c.id, c.companyName, c.address, c.openTime,c.closeTime) from CompanyEntity c")
    List<CompanyEntity> getAllCompany();
}
