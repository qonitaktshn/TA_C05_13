package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.repository.ItemFactoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugasakhir.sibusiness.rest.ItemFactoryDetail;
import apap.tugasakhir.sibusiness.rest.Setting;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ItemFactoryRestServiceImpl implements ItemFactoryRestService {
    private WebClient webClient;

    public ItemFactoryRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.itemURL).defaultHeader("Content-Type", "application/json").build();
    }

    @Autowired
    ItemFactoryDB itemFactoryDB;

    @Override
    public ItemFactoryModel createItemFactory(ItemFactoryModel itemFactory) {
        itemFactory.setStatus(0);
        return itemFactoryDB.save(itemFactory);
    }

    @Override
    public ItemFactoryDetail requestItemFactory(ItemFactoryDetail itemFactory) {
        
        ItemFactoryDetail post = this.webClient.post()
                            .uri("/api/item")
                            .body(Mono.just(itemFactory), ItemFactoryDetail.class)
                            .retrieve().bodyToMono(ItemFactoryDetail.class).block();

        return post;
    }

}
