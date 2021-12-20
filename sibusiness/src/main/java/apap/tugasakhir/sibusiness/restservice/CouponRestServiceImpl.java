package apap.tugasakhir.sibusiness.restservice;

import apap.tugasakhir.sibusiness.model.CouponModel;
import apap.tugasakhir.sibusiness.model.CouponTypeModel;
import apap.tugasakhir.sibusiness.repository.CouponDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@Service
@Transactional
public class CouponRestServiceImpl implements CouponRestService {
    @Autowired
    private CouponDB couponDB;

    @Override
    public CouponModel getCouponByNoCoupon(Long noCoupon) {
        Optional<CouponModel> coupon = couponDB.findById(noCoupon);
        if (coupon.isPresent()) {
            return coupon.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void assignCouponCodeByNoCoupon(Long noCoupon) {
        CouponModel coupon = getCouponByNoCoupon(noCoupon);

        String takeDiscAmount = String.format("%.2f", coupon.getDiscountAmount());
        takeDiscAmount = takeDiscAmount.replace(".","");
        String formattedDiscAmount = "";
        if (takeDiscAmount.length() == 1) {
            formattedDiscAmount = "00" + takeDiscAmount;
        } else if (takeDiscAmount.length() == 2) {
            formattedDiscAmount = "0" + takeDiscAmount;
        } else {
            formattedDiscAmount = takeDiscAmount;
        }

        String formattedUseDay = coupon.getListCouponType().get(0).getUseDay().substring(0, 3).toUpperCase();

        String formattedExpiryDay = String.format("%02d", coupon.getExpiryDate().getDayOfMonth());
        String formattedExpiryMonth = String.format("%02d", coupon.getExpiryDate().getMonthValue());
        String formattedExpiryYear = String.format("%02d", coupon.getExpiryDate().getYear());
        String formattedExpiryDate = formattedExpiryDay + formattedExpiryMonth + formattedExpiryYear.substring(formattedExpiryYear.length() - 2);

        String generatedCode = "DISC" + formattedDiscAmount + formattedUseDay + formattedExpiryDate;
        coupon.setCouponCode(generatedCode);
        couponDB.save(coupon);
    }

    @Override
    public CouponModel getCouponByUseDay(CouponModel cou) {
        List<String> listUseDayofACoupon = new ArrayList<>();

        for (CouponTypeModel coutype : cou.getListCouponType()) {
            listUseDayofACoupon.add(coutype.getUseDay());
        }

        String fullDayName = LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        if (listUseDayofACoupon.contains(fullDayName)) {
            return cou;
        }
        return null;
    }

    @Override
    public List<CouponModel> getListCodedCoupon() {
        List<CouponModel> listCouponRaw = couponDB.findAll();
        List<CouponModel> listCouponFiltered = new ArrayList<>();

        for (CouponModel cou : listCouponRaw) {
            if (cou.getStatus()) {
                assignCouponCodeByNoCoupon(cou.getId());
                if (getCouponByUseDay(cou) != null) {
                    listCouponFiltered.add(getCouponByUseDay(cou));
                }
            }
        }
        return listCouponFiltered;
    }
}
