package apap.tugasakhir.sibusiness.restcontroller;

import apap.tugasakhir.sibusiness.model.CouponModel;
import apap.tugasakhir.sibusiness.restservice.CouponRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CouponRestController {
    @Autowired
    private CouponRestService couponRestService;

    @GetMapping(value = "/coupon/allCoupon")
    private List<CouponModel> getListCodedCoupon() {
        return couponRestService.getListCodedCoupon();
    }
}
