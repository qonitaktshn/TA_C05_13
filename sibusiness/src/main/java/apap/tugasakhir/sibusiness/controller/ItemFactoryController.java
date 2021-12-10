package apap.tugasakhir.sibusiness.controller;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.rest.ItemFactoryDetail;
import apap.tugasakhir.sibusiness.restservice.ItemFactoryRestService;
import apap.tugasakhir.sibusiness.service.ItemFactoryService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/item-factory")
public class ItemFactoryController {
    @Autowired
    private ItemFactoryService itemFactoryService;

    @Autowired
    private ItemFactoryRestService itemFactoryRestService;

    @GetMapping("/view-all")
    private String getAllItemFactory(Model model) {
        List<ItemFactoryModel> listItemFactory = itemFactoryService.getListItemFacor();
        model.addAttribute("listItemFactory", listItemFactory);
        return "viewall-item-factory";
    }

    @GetMapping(value = "/submit/{id}")
    public String requestItemFactory(
        @PathVariable Long id,
        RedirectAttributes redirectAttributes,
        Model model
    ) {
        ItemFactoryModel itemFactory = itemFactoryService.getItemFactoryById(id);
        itemFactory.setStatus(1);
        ItemFactoryDetail itemDetail = new ItemFactoryDetail();
        itemDetail.setNama(itemFactory.getNama());
        itemDetail.setHarga(itemFactory.getHarga());
        itemDetail.setKategori(itemFactory.getKategori());
        itemDetail.setStok(itemFactory.getStok());
        
        ItemFactoryDetail response = itemFactoryRestService.requestItemFactory(itemDetail);
        System.out.println(response.getHarga());
        System.out.println(itemDetail.getNama());
      
        return "add-item-factory";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteItemFactory(
        @PathVariable Long id,
        RedirectAttributes redirectAttributes,
        Model model) {
            ItemFactoryModel itemFactory = itemFactoryService.getItemFactoryById(id);
            itemFactory.setStatus(2);
            itemFactoryService.delete(itemFactory);
            
            model.addAttribute("itemFactory", itemFactory);
            return "redirect:/item-factory/view-all";
        }

        
}
