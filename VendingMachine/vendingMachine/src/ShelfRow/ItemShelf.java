package ShelfRow;

public class ItemShelf {
    Item item;
    int code;
    boolean isSoldout;

    public ItemShelf(int code) {
        this.item = null;
        this.code = code;
        this.isSoldout = true;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSoldout() {
        return isSoldout;
    }

    public void setSoldout(boolean soldout) {
        isSoldout = soldout;
    }
}
