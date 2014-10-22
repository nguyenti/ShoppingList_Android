package hu.ait.tiffanynguyen.shoppinglist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notetable.db";
    private static final int DATABASE_VERSION = 1;

    public ItemDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        ItemTable.onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        ItemTable.onUpgrade(database, oldVersion, newVersion);
    }
}
