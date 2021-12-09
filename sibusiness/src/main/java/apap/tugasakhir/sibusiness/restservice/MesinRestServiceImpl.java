package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.rest.ItemDetail;
import apap.tugasakhir.sibusiness.rest.MesinDetail;
import apap.tugasakhir.sibusiness.rest.Setting;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class MesinRestServiceImpl implements MesinRestService{
    private final WebClient webClient;

    public MesinRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.mesinURL).build();
    }

    @Override
    public List<MesinDetail> getListMesin() {
        JsonNode jsonNode = this.webClient.get().uri("/sifactory/v1/list-mesin").retrieve().bodyToMono(JsonNode.class).block();
        List<MesinDetail> mesinList = new ArrayList<MesinDetail>();
        for (JsonNode j : jsonNode) {
            MesinDetail mesin = new MesinDetail();
            mesin.setIdMesin(j.get("idMesin").asInt());
            mesin.setNama(j.get("nama").asText());
            mesin.setIdKategori(j.get("idKategori").asInt());
            LocalDate date = LocalDate.parse(j.get("tanggalDibuat").asText());
            mesin.setTanggalDibuat(date);
            mesin.setKapasitas(j.get("kapasitas").asInt());

            mesinList.add(mesin);
        }
        return mesinList;
    }

    @Override
    public List<MesinDetail> getListMesinByKategori(int kategori) {
        List<MesinDetail> listMesin = getListMesin();
        Map<Integer, List<MesinDetail>> mesinByKategori = new HashMap<>();
        for (MesinDetail mesin:listMesin) {
            List<MesinDetail> mesinMap = mesinByKategori.get(mesin.getIdKategori());
            if (mesinMap == null) {
                mesinMap = new ArrayList<>();
                mesinByKategori.put(mesin.getIdKategori(), mesinMap);
            }
            mesinMap.add(mesin);
        }
        return mesinByKategori.get(kategori);
    }

}
