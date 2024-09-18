package Product;

import java.util.List;

public class Product {
    int productId;
    int srcPincode;
    int price;
    List<Integer> service;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSrcPincode() {
        return srcPincode;
    }

    public void setSrcPincode(int srcPincode) {
        this.srcPincode = srcPincode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Integer> getService() {
        return service;
    }

    public void setService(List<Integer> service) {
        this.service = service;
    }
}
