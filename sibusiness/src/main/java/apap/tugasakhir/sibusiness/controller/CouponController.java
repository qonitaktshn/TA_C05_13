package apap.tugasakhir.sibusiness.controller;

import apap.tugasakhir.sibusiness.model.CouponModel;
import apap.tugasakhir.sibusiness.model.UserModel;
import apap.tugasakhir.sibusiness.service.CouponService;
import apap.tugasakhir.sibusiness.service.CouponTypeService;
import apap.tugasakhir.sibusiness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @Autowired
    CouponTypeService couponTypeService;

    @Autowired
    UserService userService;

    @GetMapping("/add")
    public String addCouponForm(Model model) {
        CouponModel coupon = new CouponModel();
        model.addAttribute("listAllCouponType", couponTypeService.getListCouponType());
        model.addAttribute("coupon", coupon);
        return "form-add-coupon";
    }

    @PostMapping("/add")
    public String addCouponSubmit(@ModelAttribute CouponModel coupon, RedirectAttributes redirectAttributes, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Staff_Marketing"))) {
            UserModel creator = userService.getUserByUsername(auth.getName());
            coupon.setCreator(creator);
            couponService.addCoupon(coupon);
            //redirectAttributes.addFlashAttribute("message", "Coupon berhasil ditambahkan.");
            //return "redirect:/coupon/view-all-created";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Staff_Product"))) {
            UserModel creator = userService.getUserByUsername(auth.getName());
            coupon.setCreator(creator);
            couponService.addCoupon(coupon);
            //redirectAttributes.addFlashAttribute("message", "Coupon berhasil direquest.");
            //return "redirect:/coupon/view-all-requested";
        }
        return "redirect:/";
    }
}
