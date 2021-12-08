package apap.tugasakhir.sibusiness.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.catalina.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CabangDetail {
    @JsonProperty("nama")
    private String nama;

    @JsonProperty("alamat")
    private String alamat;

    @JsonProperty("ukuran")
    private Integer ukuran;

    @JsonProperty("no_telp")
    private String noTelp;
}
