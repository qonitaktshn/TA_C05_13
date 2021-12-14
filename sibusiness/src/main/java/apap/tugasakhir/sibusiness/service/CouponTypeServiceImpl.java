package apap.tugasakhir.sibusiness.service;

import apap.tugasakhir.sibusiness.model.CouponTypeModel;
import apap.tugasakhir.sibusiness.repository.CouponTypeDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CouponTypeServiceImpl implements CouponTypeService {

    @Autowired
    CouponTypeDB couponTypeDB;

    @Override
    public List<CouponTypeModel> getListCouponType() {
        return couponTypeDB.findAll();
    }
}
