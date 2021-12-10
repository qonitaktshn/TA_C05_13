package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.CabangModel;
import apap.tugasakhir.sibusiness.repository.CabangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CabangServiceImpl implements CabangService {

    @Autowired
    private CabangDB cabangDB;

    @Override
    public CabangModel addCabang(CabangModel cabang) {
        return cabangDB.save(cabang);
    }

    @Override
    public CabangModel getCabangById(Long id) {
        Optional<CabangModel> cabang = cabangDB.findById(id);
        if (cabang.isPresent()) {
            return cabang.get();
        }
        return null;
    }
}