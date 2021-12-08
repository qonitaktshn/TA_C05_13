package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.rest.ItemDetail;
import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import reactor.core.publisher.Mono;
import java.util.List;

public interface ItemRestService {
    // Mono<ItemDetail> addItem();
    void addItem(ItemFactoryModel item);
    List<ItemFactoryModel> getListItem();
}
