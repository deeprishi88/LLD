import cart.Cart;
import inventory.Inventory;
import order.Order;
import order.OrderController;
import product.ProductCategory;
import user.User;
import user.UserController;
import warehouse.Warehouse;
import warehouse.WarehouseController;
import warehouse.WarehouseSelectionStrategy;

import java.util.List;

public class ProductDeliverySystem {
    UserController userController;
    WarehouseController warehouseController;
    OrderController orderController;

    ProductDeliverySystem(List<User> userList, List<Warehouse> warehouseList){
        userController = new UserController(userList);
        warehouseController = new WarehouseController(warehouseList , null);
        orderController = new OrderController();
    }

    //get user object
    public User getUser(int userId){
        return userController.getUser(userId);
    }

    //get warehouse
    public Warehouse getWarehouse(WarehouseSelectionStrategy warehouseSelectionStrategy){
        return warehouseController.selectWarehouse(warehouseSelectionStrategy);

    }

    //get inventory
    public Inventory getInventory(Warehouse warehouse){
        return warehouse.inventory;

    }

    //add product to cart
    public void addProductToCart(User user, ProductCategory product, int count){
        Cart cart = user.getUserCart();
        cart.addItemInCart(product.productCategoryId, count);
    }

    //place order
    public Order placeOrder(User user, Warehouse warehouse){
        return orderController.createNewOrder(user, warehouse);
    }

    public void checkout(Order order){
        order.checkout();
    }

}
