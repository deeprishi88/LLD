package Inventory;

import ShelfRow.Item;
import ShelfRow.ItemShelf;

public class Inventory {
    ItemShelf[] inventory = null;

    public Inventory(int itemCount){
        this.inventory = new ItemShelf[itemCount];
        initialEmptyInventory();
    }

    public ItemShelf[] getInventory() {
        return inventory;
    }

    public void setInventory(ItemShelf[] inventory) {
        this.inventory = inventory;
    }

    public void initialEmptyInventory() {
        int startCode = 101;
        for (int i = 0; i < inventory.length; i++) {
            ItemShelf space = new ItemShelf(startCode);
            inventory[i]= space;
            startCode++;
        }
    }

    public void addItem(Item item, int codeNumber) throws Exception {
        for(ItemShelf shelf : inventory){
            if(shelf.getCode() == codeNumber && shelf.isSoldout()){
                shelf.setItem(item);
                shelf.setSoldout(false);
            } else {
                throw new Exception("already item is present, you can not add item here");
            }
        }
    }

    public Item getItem(int codeNumber) throws Exception {

        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                if (itemShelf.isSoldout()) {
                    throw new Exception("item already sold out");
                } else {
                    return itemShelf.getItem();
                }
            }
        }
        throw new Exception("Invalid Code");
    }

    public void updateSoldOutItem(int codeNumber){
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                itemShelf.setSoldout(true);
            }
        }
    }
}
