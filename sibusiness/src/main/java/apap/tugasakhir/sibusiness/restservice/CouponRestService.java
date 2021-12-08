package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.model.CouponModel;

import java.util.List;

public interface CouponRestService {
    CouponModel getCouponByNoCoupon(Long noCoupon);
    void assignCouponCodeByNoCoupon(Long noCoupon);
    CouponModel getCouponByUseDay(CouponModel coupon);
    List<CouponModel> getListCodedCoupon();
}
