package apap.tugasakhir.sibusiness.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MesinDetail {
    @JsonProperty("idMesin")
    private int idMesin;

    @JsonProperty("nama")
    private String nama;

    @JsonProperty("idKategori")
    private int idKategori;

    @JsonProperty("tanggalDibuat")
    private LocalDate tanggalDibuat;

    @JsonProperty("kapasitas")
    private int kapasitas;
}
