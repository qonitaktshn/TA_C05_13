package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.repository.ItemFactoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemFactoryServiceImpl implements ItemFactoryService{
    @Autowired
    private ItemFactoryDB itemFactorDB;

    @Override
    public List<ItemFactoryModel> getListItemFacor() {
        return itemFactorDB.findAll();
    }

    @Override
    public void delete(ItemFactoryModel itemFactory) {
        itemFactorDB.delete(itemFactory);
    }

    @Override
    public ItemFactoryModel getItemFactoryById(Long id) {
        Optional<ItemFactoryModel> itemFactory = itemFactorDB.findById(id);
        if (itemFactory.isPresent()) {
            return itemFactory.get();
        } 
        return null;
    }
}
