package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.rest.ItemDetail;

import java.util.List;
import java.util.Map;

public interface ItemRestService {
    Map<String, List<ItemDetail>> retrieveListItem();
    ItemDetail getItemByUUID(String uuid);
}
