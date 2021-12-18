package apap.tugasakhir.sibusiness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugasakhir.sibusiness.model.CabangModel;
import apap.tugasakhir.sibusiness.rest.CabangDetail;
import apap.tugasakhir.sibusiness.restservice.CabangRestService;
import apap.tugasakhir.sibusiness.service.CabangService;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CabangController {

    @Autowired
    CabangRestService cabangRestService;

    @Autowired
    CabangService cabangService;

    @GetMapping("/cabang")
    public String addCabangForm(Model model) {
        CabangModel cabang = new CabangModel();
        model.addAttribute("cabang", cabang);
    
        return "form-add-cabang";
    }

    @PostMapping(value="/cabang")
    public String addCabangSubmit(
        @ModelAttribute CabangModel cabang,
        RedirectAttributes redirectAttributes,
            Model model
    ) {
        CabangDetail cabangDetail = new CabangDetail();
        cabangService.addCabang(cabang);

        CabangModel newCabang = cabangService.getCabangById(cabang.getId());
        cabangDetail.setNama(newCabang.getNama());
        cabangDetail.setAlamat(newCabang.getAlamat());
        cabangDetail.setNoTelp(newCabang.getNoTelp());
        cabangDetail.setUkuran(newCabang.getUkuran());
        cabangDetail.setStatus(0);
        
        CabangDetail cabangPost = cabangRestService.requestCabang(cabangDetail);
        System.out.println(cabangPost.getNama());
        
        model.addAttribute("cabang", cabang);         
        return "add-cabang";
    }
}