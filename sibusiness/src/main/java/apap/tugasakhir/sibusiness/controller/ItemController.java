package apap.tugasakhir.sibusiness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import apap.tugasakhir.sibusiness.rest.ItemDetail;
import org.springframework.ui.Model;
import java.util.Map;

import apap.tugasakhir.sibusiness.restservice.ItemRestService;

@Controller
@RequestMapping("/api/v1")

public class ItemController {
    @Autowired
    private ItemRestService itemRestService;

    @GetMapping(value = "/list-item")
    private String getListItem(Model model) {
        Map<String, List<ItemDetail>> itemsByKategori = itemRestService.retrieveListItem();
        model.addAttribute("itemsByKategori", itemsByKategori);
        return "viewall-item";
    }

    @GetMapping(value = "/detail-item/{uuid}")
    private String getDetailItem(
            @PathVariable String uuid,
            Model model) {
        ItemDetail item = itemRestService.getItemByUUID(uuid);
        model.addAttribute("item", item);
        return "detail-item";

    }
}
