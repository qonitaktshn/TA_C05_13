package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.rest.MesinDetail;

import java.util.List;
import java.util.Map;

public interface MesinRestService {
    List<MesinDetail> getListMesin();
    List<MesinDetail> getListMesinByKategori(int kategori);
}
