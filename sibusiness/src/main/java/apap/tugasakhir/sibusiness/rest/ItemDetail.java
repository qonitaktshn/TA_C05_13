package apap.tugasakhir.sibusiness.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDetail {

    @JsonProperty("harga")
    private Integer harga;

    @JsonProperty("kategori")
    private Integer kategori;

    @JsonProperty("nama")
    private String nama;

    @JsonProperty("stok")
    private Integer stok;
}
