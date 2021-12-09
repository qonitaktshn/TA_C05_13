package apap.tugasakhir.sibusiness.controller;

import apap.tugasakhir.sibusiness.rest.MesinDetail;
import apap.tugasakhir.sibusiness.restservice.MesinRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mesin")
public class MesinController {
    @Autowired
    private MesinRestService mesinRestService;

   @RequestMapping(value = {"/view-all", "/view-all/{idKategori}"})
    private String getListMesin(@PathVariable(required = false) Integer idKategori, Model model) {
       List<MesinDetail> listMesin = mesinRestService.getListMesin();
       List<Integer> listKategori = new ArrayList<>();
       for (MesinDetail mesin:listMesin) {
           if (!listKategori.contains(mesin.getIdKategori())){
               listKategori.add(mesin.getIdKategori());
           }
       }
       if (idKategori == null) {
           model.addAttribute("listMesin", listMesin);
       }
       else {
           List<MesinDetail> MesinByKategori = mesinRestService.getListMesinByKategori(idKategori);
           model.addAttribute("listMesin", MesinByKategori);
       }
       model.addAttribute("listKategori", listKategori);
        return "viewall-mesin";
    }
}
