package hu.ait.tiffanynguyen.shoppinglist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ItemDataSource {

    // Database fields
    private SQLiteDatabase database;
    private ItemDBHelper dbHelper;
    private String[] allColumns = { ItemTable.COLUMN_ID,
            ItemTable.COLUMN_NAME, ItemTable.COLUMN_DESC,
            ItemTable.COLUMN_TYPE, ItemTable.COLUMN_QUANTITY, ItemTable.COLUMN_PRICE,
            ItemTable.COLUMN_BOUGHT };

    public ItemDataSource(Context context) {
        dbHelper = new ItemDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Item createItem(Item item) {
        return createItem(item.getName(), item.getDescription(), item.getType(), item.getQuantity(),
                item.getPrice(), item.isBought());
    }

    private Item createItem(String anItem, String aDesc, Item.ItemType aType, int aQuantity,
                           float aPrice, boolean aBought) {
        ContentValues values = new ContentValues();
        values.put(ItemTable.COLUMN_NAME, anItem);
        values.put(ItemTable.COLUMN_DESC, aDesc);
        values.put(ItemTable.COLUMN_TYPE, aType.getValue());
        values.put(ItemTable.COLUMN_QUANTITY, aQuantity);
        values.put(ItemTable.COLUMN_PRICE, aPrice);
        values.put(ItemTable.COLUMN_BOUGHT, aBought ? 1 : 0);
        long insertId = database.insert(ItemTable.TABLE_ITEM, null,
                values);
        Cursor cursor = database.query(ItemTable.TABLE_ITEM,
                allColumns, ItemTable.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Item newItem = cursorToItem(cursor);
        cursor.close();
        return newItem;
    }

    private Item cursorToItem(Cursor cursor) {
        Item item = new Item();
        item.setId(cursor.getLong(0));
        item.setName(cursor.getString(1));
        item.setDescription(cursor.getString(2));
        item.setType(cursor.getInt(3));
        item.setQuantity(cursor.getInt(4));
        item.setPrice(cursor.getFloat(5));
        item.setBought(cursor.getInt(6));
        return item;
    }

    public void deleteItem(Item item) {
        long id = item.getId();
        database.delete(ItemTable.TABLE_ITEM, ItemTable.COLUMN_ID
                + " = " + id, null);
    }

    public void changeBought(int position, boolean value) {
        Cursor cursor = database.query(ItemTable.TABLE_ITEM,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        int i = 0;
        while (i++ < position) {
            if (cursor.isAfterLast()) {
                cursor.close();
                return;
            }
            cursor.moveToNext();
        }
        Item item = cursorToItem(cursor);
        ContentValues values = new ContentValues();
        values.put(ItemTable.COLUMN_NAME, item.getName());
        values.put(ItemTable.COLUMN_DESC, item.getDescription());
        values.put(ItemTable.COLUMN_TYPE, item.getType().getValue());
        values.put(ItemTable.COLUMN_QUANTITY, item.getQuantity());
        values.put(ItemTable.COLUMN_PRICE, item.getPrice());
        values.put(ItemTable.COLUMN_BOUGHT, value);

        database.update(ItemTable.TABLE_ITEM, values, ItemTable.COLUMN_ID + "=" + item.getId(), null);

    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<Item>();

        Cursor cursor = database.query(ItemTable.TABLE_ITEM,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Item item = cursorToItem(cursor);
            items.add(item);
            cursor.moveToNext();
        }
        // Ne felejtsük bezárni!
        cursor.close();
        return items;
    }
}

