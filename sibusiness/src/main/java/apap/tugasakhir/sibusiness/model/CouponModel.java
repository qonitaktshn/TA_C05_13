package apap.tugasakhir.sibusiness.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="coupon")
public class CouponModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @JsonIgnore
    private Boolean status;

    @Size(max = 16)
    @Column(name = "coupon_code")
    private String couponCode;

    @NotNull
    @Size(max = 20)
    @Column(name = "coupon_name", nullable = false)
    private String couponName;

    @NotNull
    @Column(nullable = false)
    private Float discountAmount;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    // Relasi dengan UserModel
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "creator", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel creator;

    // Relasi dengan CouponTypeModel
    @ManyToMany
    @JoinTable(
            name = "coupon_coupontype",
            joinColumns = @JoinColumn(name = "no_coupon"),
            inverseJoinColumns = @JoinColumn(name = "no_coupontype"))
    @JsonIgnore
    List<CouponTypeModel> listCouponType;
}
