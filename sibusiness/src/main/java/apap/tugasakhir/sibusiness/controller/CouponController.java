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

import java.util.ArrayList;
import java.util.List;

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
            redirectAttributes.addFlashAttribute("message", "Coupon berhasil ditambahkan.");
            return "redirect:/coupon/list-coupon";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Staff_Product"))) {
            UserModel creator = userService.getUserByUsername(auth.getName());
            coupon.setCreator(creator);
            couponService.addCoupon(coupon);
            redirectAttributes.addFlashAttribute("message", "Coupon berhasil direquest.");
            return "redirect:/coupon/list-coupon-request";
        }
        return "redirect:/";
    }

    @GetMapping("/list-coupon")
    public String getListCoupon(Model model) {
        List<CouponModel> listCoupon = couponService.getListCoupon();
        List<CouponModel> listCouponTrueStatus = new ArrayList<>();
        for (CouponModel coupon: listCoupon) {
            if (coupon.getStatus().equals(true)) {
                listCouponTrueStatus.add(coupon);
            }
        }
        model.addAttribute("listCoupon", listCouponTrueStatus);
        return "viewall-coupon";
    }

    @GetMapping("list-coupon-request")
    public String getListRequestCoupon(Model model) {
        List<CouponModel> listRequestCoupon = couponService.getListRequestCoupon();
        model.addAttribute("listRequest", listRequestCoupon);
        return "viewall-request-coupon";
    }

    @GetMapping("/detail-coupon/{id}")
    private String getDetailCoupon(
            @PathVariable Long id,
            Model model) {
        CouponModel coupon = couponService.getCouponById(id);
        model.addAttribute("coupon", coupon);
        return "detail-coupon";
    }

    @GetMapping("/request/{id}")
    private String getDetailReqCoupon(
            @PathVariable Long id,
            Model model) {
        CouponModel coupon = couponService.getCouponById(id);
        model.addAttribute("coupon", coupon);
        return "detail-req-coupon";
    }
    

    @GetMapping("/delete/{id}")
    private String deleteCoupon(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes
            ) {
        CouponModel coupon = couponService.getCouponById(id);
        couponService.deleteCoupon(coupon);
        redirectAttributes.addFlashAttribute("message", "Coupon berhasil dihapus");
        return "redirect:/coupon/list-coupon";
    }

    @GetMapping("deletereq/{id}")
    public String deleteCouponReq(
        @PathVariable Long id,
        RedirectAttributes redirectAttributes
    ) {
        CouponModel coupon = couponService.getCouponById(id);
        couponService.deleteCoupon(coupon);
        redirectAttributes.addFlashAttribute("message", "Request Coupon berhasil dihapus");
        return "redirect:/coupon/list-coupon-request";
    }

    @GetMapping("/approve/{id}")
    public String approveCoupon(
        @PathVariable Long id,
        RedirectAttributes redirectAttributes
    ) {
        System.out.println(id);
        couponService.approveCoupon(id);
        redirectAttributes.addFlashAttribute("message", "Coupon berhasil dibuat");
        return "redirect:/coupon/list-coupon";
    }

    @GetMapping("/update/{id}")
    public String updateCouponForm(@PathVariable Long id, Model model) {
        CouponModel coupon = couponService.getCouponById(id);
        model.addAttribute("coupon", coupon);
        return "form-update-coupon";
    }

    @PostMapping("/update")
    public String updateCouponSubmit(
            @ModelAttribute CouponModel coupon, RedirectAttributes redirectAttributes
    ){
        couponService.updateCoupon(coupon);
        redirectAttributes.addFlashAttribute("message", "Update Coupon berhasil disimpan!");
        return "redirect:/coupon/list-coupon";
    }


}
