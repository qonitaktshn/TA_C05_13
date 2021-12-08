package apap.tugasakhir.sibusiness.restcontroller;

import apap.tugasakhir.sibusiness.rest.MesinDTO;
import apap.tugasakhir.sibusiness.restservice.MesinRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class MesinRestController {
    @Autowired
    private MesinRestService mesinRestService;

    @GetMapping(value="/list-mesin")
    private Mono<MesinDTO> retrieveListMesin() {
        return mesinRestService.retrieveListMesin();
    }
}
