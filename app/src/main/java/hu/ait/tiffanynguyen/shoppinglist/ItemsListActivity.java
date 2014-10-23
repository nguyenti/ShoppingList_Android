package hu.ait.tiffanynguyen.shoppinglist;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import hu.ait.tiffanynguyen.shoppinglist.adapter.ItemAdapter;
import hu.ait.tiffanynguyen.shoppinglist.data.Item;
import hu.ait.tiffanynguyen.shoppinglist.data.ItemDataSource;


public class ItemsListActivity extends ListActivity {

    public static final int REQUEST_NEW_ITEM = 100;
    private ItemDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        datasource = new ItemDataSource(this);
        datasource.open();
        List<Item> itemList = datasource.getAllItems();
        setListAdapter(new ItemAdapter(getApplicationContext(),itemList));

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // selected item
                Item item = (Item) getListAdapter().getItem(position);

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getBaseContext(), ItemDetailActivity.class);
                // sending data to new activity
                i.putExtra("item", item);
                startActivity(i);
            }
        });

//        CheckBox cb = get
    }

    @Override
    protected void onDestroy() {
        List<Item> itemList = datasource.getAllItems();

        for (int i = 0; i < itemList.size(); i++) {
            datasource.changeBought(i, ((Item) getListAdapter().getItem(i)).isBought());
        }
        datasource.close();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Item p = (Item) data.getSerializableExtra("KEY_ITEM");

            ItemAdapter adapter = (ItemAdapter) getListAdapter();
            Item item;
            item = datasource.createItem(p);
            adapter.addItem(item);
            adapter.notifyDataSetChanged();

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.items_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();
        if (id == R.id.action_new_item) {
            Intent i = new Intent();
            i.setClass(this, CreateNewItemActivity.class);
            // REQUEST... is a request code to get results from a certain activity
            startActivityForResult(i, REQUEST_NEW_ITEM);
            return true;
        } else if (id == R.id.action_delete_selected) {
            /*
            * Deletion adapted from:
            * https://stackoverflow.com/questions/2558591/remove-listview-items-in-android
            */
            AlertDialog.Builder adb=new AlertDialog.Builder(ItemsListActivity.this);
            adb.setTitle("Delete?");
            adb.setMessage("Are you sure you want to delete the selected item(s)?");
            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ItemAdapter adapter = (ItemAdapter) getListAdapter();
                    Item item;
                    for (int i = 0; i < adapter.getCount(); i++) {
                        if (adapter.isChecked(i)) {
                            item = (Item) adapter.getItem(i);
                            datasource.deleteItem(item);
                            adapter.removeItem(i--);
                        }
                    }
                    ((ItemAdapter) getListAdapter()).notifyDataSetChanged();
                }});
            adb.show();
            ((ItemAdapter) getListAdapter()).notifyDataSetChanged();
            return true;
        } else if (id == R.id.action_delete_all) {
            AlertDialog.Builder adb=new AlertDialog.Builder(ItemsListActivity.this);
            adb.setTitle("Delete?");
            adb.setMessage("Are you sure you want to delete all items?");
            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ItemAdapter adapter = (ItemAdapter) getListAdapter();
                    Item item;
                    for (int i = 0; i < adapter.getCount(); i++) {
                        item = (Item) adapter.getItem(i);
                        datasource.deleteItem(item);
                        adapter.removeItem(i--);
                    }
                    ((ItemAdapter) getListAdapter()).notifyDataSetChanged();
                }});
            adb.show();
            ((ItemAdapter) getListAdapter()).notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
