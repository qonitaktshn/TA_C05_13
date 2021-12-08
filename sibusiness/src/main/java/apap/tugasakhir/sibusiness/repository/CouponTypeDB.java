package apap.tugasakhir.sibusiness.repository;

import apap.tugasakhir.sibusiness.model.CouponTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponTypeDB extends JpaRepository<CouponTypeModel, Long> {
    CouponTypeModel findByUseDay(String useDay);
}
