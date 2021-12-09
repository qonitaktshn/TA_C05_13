package apap.tugasakhir.sibusiness.restservice;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
    public CabangDetail addCabang(CabangDetail cabang) {
        
        CabangDetail post = this.webClient.post()
                            .uri("/cabang")
                            .body(Mono.just(cabang), CabangDetail.class)
                            .retrieve().bodyToMono(CabangDetail.class).block();
        
                            // System.out.println(cabang.getNama());
                            System.out.println(post.getNama());

        return post;
    }
}
