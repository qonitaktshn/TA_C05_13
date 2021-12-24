package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.repository.ItemFactoryDB;
import apap.tugasakhir.sibusiness.repository.UserDB;
import apap.tugasakhir.sibusiness.model.UserModel;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugasakhir.sibusiness.rest.ItemFactoryDetail;
import apap.tugasakhir.sibusiness.rest.Setting;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@Transactional
public class ItemFactoryRestServiceImpl implements ItemFactoryRestService {
    private WebClient webClient;

    public ItemFactoryRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.itemURL).build();
    }

    @Autowired
    ItemFactoryDB itemFactoryDB;

    @Autowired
    UserDB userDB;

    @Override
    public ItemFactoryModel createItemFactory(ItemFactoryModel itemFactory) {
        itemFactory.setStatus(0);
        return itemFactoryDB.save(itemFactory);
    }

    @Override
    public ItemFactoryModel getItemFactoryById(Long id) {
        Optional<ItemFactoryModel> item = itemFactoryDB.findById(id);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public ItemFactoryDetail requestItemFactory(ItemFactoryDetail itemFactory) {
        
        ItemFactoryDetail post = this.webClient.post()
                            .uri("/api/item")
                            .body(Mono.just(itemFactory), ItemFactoryDetail.class)
                            .retrieve().bodyToMono(ItemFactoryDetail.class).block();
        return post;
    }

    @Override
    public void acceptItemFact(Long id) {
        ItemFactoryModel itemFact = getItemFactoryById(id);
        itemFact.setStatus(1);
    }

    @Override
    public void declineItemFact(Long id) {
        ItemFactoryModel itemFact = getItemFactoryById(id);
        itemFact.setStatus(2);
    }

    @Override
    public void setApproverService(Long id){
        UserModel user = userDB.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        ItemFactoryModel itemFact = getItemFactoryById(id);
        itemFact.setApprover(user.getUsername());
    }
}
