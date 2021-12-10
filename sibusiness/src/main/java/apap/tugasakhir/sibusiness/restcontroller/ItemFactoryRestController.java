package apap.tugasakhir.sibusiness.restcontroller;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.restservice.ItemFactoryRestServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class ItemFactoryRestController {
    @Autowired
    private ItemFactoryRestServiceImpl itemFactoryRestService;

    @PostMapping(value="/list-item-factory")
    private ItemFactoryModel createItemFactory(@RequestBody ItemFactoryModel itemFactory, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            return itemFactoryRestService.createItemFactory(itemFactory);

        }
    }
}
