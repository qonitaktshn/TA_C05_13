package apap.tugasakhir.sibusiness.restservice;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import apap.tugasakhir.sibusiness.repository.ItemDB;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import apap.tugasakhir.sibusiness.rest.Setting;

@Service
@Transactional
public class ItemRestServiceImpl implements ItemRestService {
    @Override
    public void addItem(ItemFactoryModel item){
        
    }

    @Override
    public List<ItemFactoryModel> getListItem(){
        return null;
    }
}
