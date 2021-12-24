package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.rest.ItemFactoryDetail;

public interface ItemFactoryRestService {
    ItemFactoryModel createItemFactory(ItemFactoryModel itemFactory);
    ItemFactoryDetail requestItemFactory(ItemFactoryDetail itemFactory);
    void acceptItemFact(Long id);
    void declineItemFact(Long id);
    ItemFactoryModel getItemFactoryById(Long id);
    void setApproverService(Long id);
}
