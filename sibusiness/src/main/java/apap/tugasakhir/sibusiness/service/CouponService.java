package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.CouponModel;

import java.util.List;

public interface CouponService {
    void addCoupon(CouponModel coupon);
    List<CouponModel> getListCoupon();
    CouponModel getCouponById(Long id);
    void deleteCoupon(CouponModel couponModel);
    List<CouponModel> getListRequestCoupon();
    void approveCoupon(Long id);
    void updateCoupon(CouponModel coupon);
}
