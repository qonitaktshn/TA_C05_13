package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.CouponModel;
import apap.tugasakhir.sibusiness.repository.CouponDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;

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

    @Override
    public List<CouponModel> getListCoupon() {
        return couponDB.findAll();
    }

    @Override
    public List<CouponModel> getListRequestCoupon() {
        List<CouponModel> newList = new ArrayList<CouponModel>();
        List<CouponModel> listRequest = couponDB.findAll();
        for (CouponModel c: listRequest) {
            if (c.getStatus().equals(false)) {
                newList.add(c);
                System.out.println(c);
            }
        }
        return newList;
    }

    @Override
    public CouponModel getCouponById(Long id) {
        return couponDB.findCouponModelById(id);
    }

    @Override
    public void deleteCoupon(CouponModel coupon) {
        couponDB.delete(coupon);
    }

    @Override 
    public void approveCoupon(Long id) {
        CouponModel coupon = getCouponById(id);
        coupon.setStatus(true);
    }

    @Override
    public void updateCoupon(CouponModel coupon) {
        couponDB.save(coupon);
    }
}
