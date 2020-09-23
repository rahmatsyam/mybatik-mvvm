package io.github.rahmatsyam.mybatik.data.model;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

public class BatikModel {

    @Json(name = "hasil")
    private List<ResultModel> hasil = new ArrayList<>();
    @Json(name = "total_halaman")
    private int totalHalaman;
    @Json(name = "total_element")
    private int totalElement;
    @Json(name = "min_price")
    private int minPrice;
    @Json(name = "max_price")
    private int maxPrice;

    public List<ResultModel> getHasil() {
        return hasil;
    }

    public void setHasil(List<ResultModel> hasil) {
        this.hasil = hasil;
    }

    public int getTotalHalaman() {
        return totalHalaman;
    }

    public void setTotalHalaman(int totalHalaman) {
        this.totalHalaman = totalHalaman;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
}
