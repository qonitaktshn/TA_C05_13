package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.rest.ItemDetail;
import apap.tugasakhir.sibusiness.rest.Setting;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ItemRestServiceImpl implements ItemRestService {
    private final WebClient webClient;

    public ItemRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.itemURL).build();
    }

    @Override
    public Map<String, List<ItemDetail>> retrieveListItem() {
        JsonNode jsonNode = this.webClient.get().uri("/api/item").retrieve().bodyToMono(JsonNode.class).block().get("result");
        List<ItemDetail> items = new ArrayList<ItemDetail>();
        Map<String, List<ItemDetail>> itemsByKategori = new HashMap<>();
        for (JsonNode j: jsonNode) {
            ItemDetail item = new ItemDetail();
            item.setUuid(j.get("uuid").asText());
            item.setNama(j.get("nama").asText());
            item.setHarga(j.get("harga").intValue());
            item.setStok(j.get("stok").asLong());
            item.setKategori(j.get("kategori").intValue());
            items.add(item);
        }
        for (ItemDetail item:items) {
            List<ItemDetail> itemMap = itemsByKategori.get(item.getKategori());
            if (itemMap == null) {
                itemMap = new ArrayList<>();
                itemsByKategori.put(item.getKategori().toString(), itemMap);
            }
            itemMap.add(item);
        }
        return itemsByKategori;
    }

    @Override
    public ItemDetail getItemByUUID(String uuid) {
        JsonNode jsonNode = this.webClient.get().uri("/api/item/" + uuid).retrieve().bodyToMono(JsonNode.class).block().get("result");
        ItemDetail item = new ItemDetail();
        item.setUuid(uuid);
        item.setNama(jsonNode.get("nama").asText());
        item.setHarga(jsonNode.get("harga").intValue());
        item.setStok(jsonNode.get("stok").asLong());
        item.setKategori(jsonNode.get("kategori").intValue());
        return item;
    }
}