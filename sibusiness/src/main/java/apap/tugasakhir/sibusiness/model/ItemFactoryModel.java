package apap.tugasakhir.sibusiness.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="item_factory")
@JsonIgnoreProperties(value = {"listUser"},allowSetters = true)

public class ItemFactoryModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=50)
    @Column(name="nama", nullable=false)
    private String nama;

    @Column(name="status", nullable=true)
    private Integer status;

    @NotNull
    @Column(name="stok", nullable=false)
    private Integer stok;

    @NotNull
    @Column(name="harga", nullable=false)
    private Integer harga;

    @NotNull
    @Column(name="kategori", nullable=false)
    private Integer kategori;

    @Column(name="approver", nullable = true)
    private String approver;

    //Relasi dengan User
    @OneToMany(mappedBy = "item_factory", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserModel> listUser;

}
