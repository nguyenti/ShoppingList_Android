package hu.ait.tiffanynguyen.shoppinglist.data;

import java.io.Serializable;
import java.util.Date;

import hu.ait.tiffanynguyen.shoppinglist.R;

/**
 * Created by tiffanynguyen on 10/16/14.
 */
public class Item implements Serializable {

    public enum ItemType{

        FOOD(0, R.drawable.ic_food),
        BOOK(1, R.drawable.ic_book),
        CLOTHING(2, R.drawable.ic_clothing),
        GAME(3, R.drawable.ic_games);

        private int value;
        private int iconId;

        private ItemType(int value, int iconId) {
            this.value = value;
            this.iconId = iconId;
        }

        public static ItemType fromInt(int value) {
            for (ItemType p : ItemType.values()) {
                if (p.value == value) {
                    return p;
                }
            }
            return FOOD;
        }

        public int getIconId() {
            return iconId;
        }

        public int getValue() {
            return value;
        }
    }

    private ItemType type;
    private long id;
    private String name;
    private String description;
    private int quantity;
    private float price;
    private boolean bought;

    public Item() {}

    public Item(ItemType type, String name, String description, int quantity, float price) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.bought = false;
    }

    public long getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public void setBought(int bought) {
        this.bought = (bought == 1);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setType(int type) {
        this.type = ItemType.fromInt(type);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}