package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.CouponModel;
import apap.tugasakhir.sibusiness.repository.CouponDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponDB couponDB;

    @Override
    public void addCoupon(CouponModel coupon) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Staff_Marketing"))) {
            coupon.setStatus(true);
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Staff_Product"))) {
            coupon.setStatus(false);
        }
        couponDB.save(coupon);
    }
}
