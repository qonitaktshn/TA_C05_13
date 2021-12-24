package apap.tugasakhir.sibusiness.repository;

import apap.tugasakhir.sibusiness.model.CouponModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponDB extends JpaRepository<CouponModel, Long> {
    List<CouponModel> findAll();
    CouponModel findCouponModelById(Long id);
}
