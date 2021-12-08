package apap.tugasakhir.sibusiness.controller;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.model.UserModel;
import apap.tugasakhir.sibusiness.repository.ItemFactoryDB;
import apap.tugasakhir.sibusiness.service.ItemFactoryService;
import apap.tugasakhir.sibusiness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item-factory")
public class ItemFactoryController {
    @Autowired
    private ItemFactoryService itemFactoryService;

    @GetMapping("/view-all")
    private String getAllItemFactory(Model model) {
        List<ItemFactoryModel> listItemFactory = itemFactoryService.getListItemFacor();
        model.addAttribute("listItemFactory", listItemFactory);
        return "viewall-item-factory";
    }
}
