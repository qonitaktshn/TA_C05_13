package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.rest.CabangDetail;
import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import reactor.core.publisher.Mono;
import java.util.List;

public interface CabangRestService {
    Mono<CabangDetail> addCabang(CabangDetail cabang);
    // void addCabang();
}
