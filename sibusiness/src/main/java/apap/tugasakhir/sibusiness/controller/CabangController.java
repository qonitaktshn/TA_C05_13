package apap.tugasakhir.sibusiness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String addCabangForm(Model model,
        @RequestParam(value="nama", required = false) String nama,
        @RequestParam(value= "alamat", required =false) String alamat,
        @RequestParam(value="ukuran", required = false) Integer ukuran,
        @RequestParam(value = "no_telp", required =false) String no_telp
    ) {
        CabangDetail cabang = new CabangDetail();
        cabang.setNama(nama);
        cabang.setAlamat(alamat);
        cabang.setUkuran(ukuran);
        cabang.setNoTelp(no_telp);
        cabang.setStatus(0);

        // System.out.println(cabang.getNama());

        model.addAttribute("cabang", cabang);
        return "form-add-cabang";
    }


    @PostMapping(value="/cabang/")
    public String addCabangSubmit(
        @ModelAttribute CabangDetail cabang,
        RedirectAttributes redirectAttributes,
            Model model
    ) {
        CabangDetail response = cabangService.addCabang(cabang);
        // System.out.println(response.getNama());
        return "add-cabang";
    }
}