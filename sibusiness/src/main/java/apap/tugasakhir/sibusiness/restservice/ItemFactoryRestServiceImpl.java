package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.repository.ItemFactoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemFactoryRestServiceImpl implements ItemFactoryRestService {
    @Autowired
    ItemFactoryDB itemFactoryDB;

    @Override
    public ItemFactoryModel createItemFactory(ItemFactoryModel itemFactory) {
        itemFactory.setStatus(0);
        return itemFactoryDB.save(itemFactory);
    }
}
