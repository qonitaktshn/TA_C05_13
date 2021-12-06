package apap.tugasakhir.sibusiness.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "coupontype")
public class CouponTypeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "use_day", nullable = false)
    private String useDay;

    //Relasi dengan CouponModel
    @ManyToMany(mappedBy = "listCouponType")
    List<CouponModel> listCoupon;
}
