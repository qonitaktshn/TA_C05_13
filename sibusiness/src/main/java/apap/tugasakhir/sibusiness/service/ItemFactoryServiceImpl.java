package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.repository.ItemFactoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemFactoryServiceImpl implements ItemFactoryService{
    @Autowired
    private ItemFactoryDB itemFactorDB;

    @Override
    public List<ItemFactoryModel> getListItemFacor() {
        return itemFactorDB.findAll();
    }
}
