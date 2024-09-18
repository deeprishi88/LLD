import Product.*;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Product> laptops, mobiles;

    public Inventory(){
        this.laptops = new ArrayList<>();
        this.mobiles = new ArrayList<>();
    }

    public Product getProduct(Product p, int idx){
        if(p instanceof Laptop){
            return laptops.get(idx);
        }
        else{
            return mobiles.get(idx);
        }
    }

    public void addProduct(Product p){
        if(p instanceof Laptop){
            laptops.add(p);
        }
        else{
            mobiles.add(p);
        }
    }

    public void removeProduct(Product p, int idx){
        if(p instanceof Laptop){
            laptops.remove(idx);
        }
        else{
            mobiles.remove(idx);
        }
    }

    public int check(int userPin, Product p){
        if(p instanceof Laptop){
            if(laptops.size()<=0){
                return -1;
            }
            System.out.println(laptops);
            for(int i=0;i<laptops.size();i++){
                List<Integer> ser = laptops.get(i).getService();
                if(!ser.isEmpty() && ser.contains(userPin)){
                    return i;
                }
            }
            return -1;
        }
        else{
            if(mobiles.size()<=0){
                return -1;
            }
            System.out.println(mobiles);
            for(int i=0;i<mobiles.size();i++){
                List<Integer> ser = mobiles.get(i).getService();
                if(!ser.isEmpty() && ser.contains(userPin)){
                    return i;
                }
            }
            return -1;
        }
    }
}
