package io.github.rahmatsyam.mybatik.data.model;

import com.squareup.moshi.Json;

public class ResultModel {

    @Json(name = "id")
    private int id;
    @Json(name = "nama_batik")
    private String namaBatik;
    @Json(name = "daerah_batik")
    private String daerahBatik;
    @Json(name = "makna_batik")
    private String maknaBatik;
    @Json(name = "harga_rendah")
    private Integer hargaRendah;
    @Json(name = "harga_tinggi")
    private Integer hargaTinggi;
    @Json(name = "hitung_view")
    private Integer hitungView;
    @Json(name = "link_batik")
    private String linkBatik;

    public int getId() {
        return id;
    }

    public String getNamaBatik() {
        return namaBatik;
    }

    public String getDaerahBatik() {
        return daerahBatik;
    }

    public String getMaknaBatik() {
        return maknaBatik;
    }

    public Integer getHargaRendah() {
        return hargaRendah;
    }

    public Integer getHargaTinggi() {
        return hargaTinggi;
    }

    public Integer getHitungView() {
        return hitungView;
    }

    public String getLinkBatik() {
        return linkBatik;
    }
}
