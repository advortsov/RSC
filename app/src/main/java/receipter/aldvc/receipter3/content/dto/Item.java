package receipter.aldvc.receipter3.content.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Item extends RealmObject {

    @SerializedName("quantity")
    @Expose
    private Double quantity;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("sum")
    @Expose
    private Integer sum;
    @SerializedName("name")
    @Expose
    private String name;

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
