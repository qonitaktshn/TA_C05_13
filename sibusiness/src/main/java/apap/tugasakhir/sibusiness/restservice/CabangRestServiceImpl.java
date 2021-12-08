package apap.tugasakhir.sibusiness.restservice;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import apap.tugasakhir.sibusiness.repository.ItemDB;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.rest.CabangDetail;
import apap.tugasakhir.sibusiness.rest.Setting;
import reactor.core.publisher.Mono;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
@Transactional
public class CabangRestServiceImpl implements CabangRestService {

    private WebClient webClient;

    private ObjectMapper mapper;

    public CabangRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://e77f9343-0326-432c-8f6b-cbab157359e3.mock.pstmn.io/").build();
    } 
    
    @Override
    public CabangDetail addCabang(CabangDetail cabang) {
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        data.add("nama", cabang.getNama());
        data.add("alamat", cabang.getUkuran());
        data.add("ukuran", cabang.getUkuran());
        data.add("no_telp", cabang.getNoTelp());
        
        CabangDetail response = this.webClient
            .post()
            .uri("/cabang")
            .syncBody(data)
            .retrieve()
            .bodyToMono(CabangDetail.class)
            .block();

            return response;
    }
}
