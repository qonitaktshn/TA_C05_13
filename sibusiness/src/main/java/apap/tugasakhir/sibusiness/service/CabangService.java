package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.CabangModel;

public interface CabangService {
    CabangModel addCabang(CabangModel cabang);
    CabangModel getCabangById(Long id);
}