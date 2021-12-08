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
    public Mono<CabangDetail> addCabang(CabangDetail cabang) {
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        data.add("nama", cabang.getNama());
        data.add("alamat", cabang.getUkuran());
        data.add("ukuran", cabang.getUkuran());
        data.add("no_telp", cabang.getNoTelp());
        
        System.out.println("masuk");
        return this.webClient
            .post()
            .uri("/cabang")
            // .bodyValue(cabang)
            .syncBody(data)
            .retrieve()
            .bodyToMono(CabangDetail.class);
            // .block();

            // return response;
    }
}
