package hu.ait.tiffanynguyen.shoppinglist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import hu.ait.tiffanynguyen.shoppinglist.adapter.ItemAdapter;
import hu.ait.tiffanynguyen.shoppinglist.data.Item;


public class ItemDetailActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent i = getIntent();

        Item item = (Item) i.getSerializableExtra("item");

        String name = item.getName();
        Item.ItemType icon = item.getType();
        int quantity = item.getQuantity();
        float price = item.getPrice();
        String description = item.getDescription();
        boolean bought = item.isBought();

        ImageView ivIcon = (ImageView) findViewById(R.id.ivItemIcon);
        TextView tvName = (TextView) findViewById(R.id.tvItemName);
        TextView tvQuantity = (TextView) findViewById(R.id.tvItemQuantity);
        TextView tvPrice = (TextView) findViewById(R.id.tvItemPrice);
        TextView tvDesc = (TextView) findViewById(R.id.tvItemDescription);
        TextView tvBought = (TextView) findViewById(R.id.tvBought);

        ivIcon.setImageResource(icon.getIconId());
        tvName.setText(name);
        tvQuantity.setText(Integer.toString(quantity));
        tvPrice.setText(Float.toString(price));
        tvDesc.setText(description);
        if (bought) {
            tvBought.setText("Yes");
        } else {
            tvBought.setText("No");
        }
    }

}
