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
    }

    private ItemType type;
    private String name;
    private String description;
    private int quantity;
    private float price;
    private boolean bought;

    public Item(ItemType type, String name, String description, int quantity, float price) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.bought = false;
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
}