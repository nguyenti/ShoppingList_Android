package hu.ait.tiffanynguyen.shoppinglist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.ait.tiffanynguyen.shoppinglist.adapter.ItemAdapter;
import hu.ait.tiffanynguyen.shoppinglist.data.Item;


public class ItemsListActivity extends ListActivity {

    public static final int REQUEST_NEW_ITEM = 100;
    public static final int DELETE_ITEMS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Item> itemList = new ArrayList<Item>();

        setListAdapter(new ItemAdapter(getApplicationContext(),itemList));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Item p = (Item) data.getSerializableExtra("KEY_ITEM");

            ((ItemAdapter) getListAdapter()).addItem(p);
            ((ItemAdapter) getListAdapter()).notifyDataSetChanged();

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
            adb.setMessage("Are you sure you want to delete the selected items?");
            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ((ItemAdapter) getListAdapter()).removeChecked();
                    ((ItemAdapter) getListAdapter()).notifyDataSetChanged();
                }});
            adb.show();
            ((ItemAdapter) getListAdapter()).notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
