import Product.Product;

public class Ecommerce {
    Inventory inventory;
    User user;

    public Ecommerce(Inventory inventory, User user) {
        this.inventory = inventory;
        this.user = user;
    }

    public synchronized String createOrder(Product p){
        int idx = inventory.check(user.getUserPincode(), p);
        if(idx==-1){
            return "Failed";
        }
        Product x = inventory.getProduct(p,idx);
        inventory.removeProduct(x, idx);
        return "Success productId = " + x.getProductId() + " , price = " + x.getPrice() + " , productPin = " +
                x.getSrcPincode() + " , userId =  " + user.getUserId() + " , userPin = " + user.getUserPincode();
    }
}
