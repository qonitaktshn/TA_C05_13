package apap.tugasakhir.sibusiness.restcontroller;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.rest.BaseResponse;
import apap.tugasakhir.sibusiness.restservice.ItemFactoryRestServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1")
public class ItemFactoryRestController {
    @Autowired
    private ItemFactoryRestServiceImpl itemFactoryRestService;

    @PostMapping(value = "/list-item-factory")
    private BaseResponse<ItemFactoryModel> createItemFactory(
            @RequestBody ItemFactoryModel itemFactory,
            BindingResult bindingResult) throws ParseException {
        BaseResponse<ItemFactoryModel> response = new BaseResponse<>();
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request Body has invalid type or missing field");
        } else {
            try {
                ItemFactoryModel itemFactoryNew = itemFactoryRestService.createItemFactory(itemFactory);
                response.setStatus(201);
                response.setMessage("created");
                response.setResult(itemFactoryNew);
            } catch (Exception e) {
                response.setStatus(400);
                response.setMessage(e.toString());
                response.setResult(null);
            }
            return response;
        }
    }
}
