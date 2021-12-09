package apap.tugasakhir.sibusiness.controller;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.model.UserModel;
import apap.tugasakhir.sibusiness.repository.ItemFactoryDB;
import apap.tugasakhir.sibusiness.rest.ItemFactoryDetail;
import apap.tugasakhir.sibusiness.restservice.ItemFactoryRestService;
import apap.tugasakhir.sibusiness.service.ItemFactoryService;
import apap.tugasakhir.sibusiness.service.UserService;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        ItemFactoryDetail itemDetail = new ItemFactoryDetail();
        itemDetail.setNama(itemFactory.getNama());
        itemDetail.setHarga(itemFactory.getHarga());
        itemDetail.setKategori(itemFactory.getKategori());
        itemDetail.setStok(itemFactory.getStok());
        
        model.addAttribute("id", itemFactory.getId());

        ItemFactoryDetail response = itemFactoryRestService.requestItemFactory(itemDetail);
        System.out.println(response.getNama());
        itemFactoryService.delete(itemFactory);
        
        return "add-item-factory";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteItemFactory(
        @PathVariable Long id,
        RedirectAttributes redirectAttributes,
        Model model) {
            ItemFactoryModel itemFactory = itemFactoryService.getItemFactoryById(id);
            itemFactoryService.delete(itemFactory);
            model.addAttribute("itemFactory", itemFactory);
            return "redirect:/item-factory/view-all";
        }

        
}
