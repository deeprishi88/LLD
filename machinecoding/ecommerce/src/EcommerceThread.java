import Product.Laptop;

public class EcommerceThread implements Runnable{

    private Inventory inventory;
    private User user;

    public EcommerceThread(Inventory inventory, User user) {
        this.inventory = inventory;
        this.user = user;
    }

    @Override
    public void run() {
        Ecommerce ecommerce = new Ecommerce(inventory, user);
        String str = ecommerce.createOrder(new Laptop());
        System.out.println(str);
    }
}
