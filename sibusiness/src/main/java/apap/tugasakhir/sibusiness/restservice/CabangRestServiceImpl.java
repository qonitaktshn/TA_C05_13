package apap.tugasakhir.sibusiness.restservice;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugasakhir.sibusiness.rest.CabangDetail;
import apap.tugasakhir.sibusiness.rest.Setting;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CabangRestServiceImpl implements CabangRestService {

    private WebClient webClient;

    public CabangRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.cabangURL).build();
    } 
    
    @Override
    public CabangDetail requestCabang(CabangDetail cabang) {
        
        CabangDetail post = this.webClient.post()
                            .uri("/api/v1/cabang")
                            .body(Mono.just(cabang), CabangDetail.class)
                            .retrieve().bodyToMono(CabangDetail.class).block();
        
                            System.out.println(cabang.getNama());

        return post;
    }
}
