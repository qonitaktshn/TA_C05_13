package apap.tugasakhir.sibusiness.controller;

import apap.tugasakhir.sibusiness.rest.ItemDetail;
import apap.tugasakhir.sibusiness.restservice.ItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
