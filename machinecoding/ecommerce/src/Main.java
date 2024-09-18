import Product.*;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<User> users = addUsers();
        Inventory inventory = new Inventory();
        List<Product> laptops = addLaptops();
        List<Product> mobiles = addMobiles();

        for(Product laptop : laptops){
            inventory.addProduct(laptop);
        }

        for(Product mobile : mobiles){
            inventory.addProduct(mobile);
        }

        // for multithreading test
        Thread t1 = new Thread(new EcommerceThread(inventory, users.get(0)));
        Thread t2 = new Thread(new EcommerceThread(inventory, users.get(1)));
        t1.start();
        t2.start();
    }

    public static List<User> addUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User(1, 560048);
        users.add(user1);
        User user2 = new User(2, 560045);
        users.add(user2);
        return users;
    }

    public static List<Product> addLaptops(){
        List<Product> laptops = new ArrayList<>();
        Product pl1 = new Laptop();
        pl1.setProductId(11);
        pl1.setSrcPincode(123456);
        pl1.setPrice(50000);
        List<Integer> pl1s = new ArrayList<>();
        pl1s.add(560048);
        pl1s.add(560065);
        pl1.setService(pl1s);

        laptops.add(pl1);

        Product pl2 = new Laptop();
        pl2.setProductId(12);
        pl1.setSrcPincode(123457);
        pl1.setPrice(60000);
        List<Integer> pl2s = new ArrayList<>();
        pl2s.add(560042);
        pl2s.add(345543);
        pl2.setService(pl2s);

        laptops.add(pl2);

        return laptops;
    }

    public static List<Product> addMobiles(){
        List<Product> mobiles = new ArrayList<>();
        Product pl1 = new Mobile();
        pl1.setProductId(21);
        pl1.setSrcPincode(123456);
        pl1.setPrice(50000);
        List<Integer> pl1s = new ArrayList<>();
        pl1s.add(560065);
        pl1s.add(345543);
        pl1.setService(pl1s);

        mobiles.add(pl1);

        Product pl2 = new Laptop();
        pl2.setProductId(22);
        pl1.setSrcPincode(123457);
        pl1.setPrice(60000);
        List<Integer> pl2s = new ArrayList<>();
        pl2s.add(560065);
        pl2s.add(345543);
        pl2.setService(pl2s);

        mobiles.add(pl2);

        return mobiles;
    }
}