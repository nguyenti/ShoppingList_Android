package hu.ait.tiffanynguyen.shoppinglist.data;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ItemTable {
    public static final String TABLE_ITEM = "item";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_QUANTITY = "qty";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_BOUGHT = "bought";

    private static final String DATABASE_CREATE = "create table " + TABLE_ITEM
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_TYPE + " integer, " + COLUMN_DESC
            + " text, " + COLUMN_QUANTITY + " integer, "
            + COLUMN_PRICE + " float, " + COLUMN_BOUGHT
            + " integer" + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(ItemTable.class.getName(), "Upgradingfrom version " + oldVersion
                + " to " + newVersion);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        onCreate(database);
    }
}

