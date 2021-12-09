package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.rest.MesinDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class MesinRestServiceImpl implements MesinRestService{
    @Override
    public Mono<MesinDTO> retrieveListMesin() {
        return null;
    }
}
