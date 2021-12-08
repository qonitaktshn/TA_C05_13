package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.rest.MesinDTO;
import reactor.core.publisher.Mono;

public interface MesinRestService {
    Mono<MesinDTO> retrieveListMesin();
}
