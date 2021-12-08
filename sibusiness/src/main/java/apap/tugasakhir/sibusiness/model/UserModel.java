package apap.tugasakhir.sibusiness.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="pengguna")

public class UserModel implements Serializable {
    @Id
    @Size(max = 200)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(max = 200)
    @Lob
    @Column(name = "password", nullable = false)
    private String password;

    // Relasi dengan RoleModel
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel role;

    // Relasi dengan CouponModel
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CouponModel> userCoupon;

    // Relasi dengan ItemFactory
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ItemFactoryModel item_factory;
}