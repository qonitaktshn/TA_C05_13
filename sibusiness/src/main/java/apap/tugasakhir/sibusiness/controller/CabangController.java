package apap.tugasakhir.sibusiness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import apap.tugasakhir.sibusiness.rest.CabangDetail;
import apap.tugasakhir.sibusiness.restservice.CabangRestService;
import reactor.core.publisher.Mono;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CabangController {

    @Autowired
    CabangRestService cabangService;

    @GetMapping("/cabang")
    public String addCabangForm(Model model) {
        CabangDetail cabang = new CabangDetail();
        model.addAttribute("cabang", cabang);
        return "form-add-cabang";
    }

    @PostMapping(value="/cabang/")
    public String addCabangSubmit(
        @ModelAttribute CabangDetail cabang,
        RedirectAttributes redirectAttributes,
            Model model
    ) {
        System.out.println(cabang.getNama());
        Mono<CabangDetail> response = cabangService.addCabang(cabang);
        System.out.println(response.equals(cabang));
        return "form-add-cabang";
    }
}