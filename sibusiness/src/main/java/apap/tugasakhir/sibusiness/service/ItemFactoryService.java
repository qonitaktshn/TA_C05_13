package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;

import java.util.List;

public interface ItemFactoryService {
    List<ItemFactoryModel> getListItemFacor();
    void delete(ItemFactoryModel itemFactory);
    ItemFactoryModel getItemFactoryById(Long id);
}
