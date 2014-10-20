package hu.ait.tiffanynguyen.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import hu.ait.tiffanynguyen.shoppinglist.adapter.MoneyValueFilter;
import hu.ait.tiffanynguyen.shoppinglist.data.Item;


public class CreateNewItemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);

        final Spinner spinnerItemType = (Spinner) findViewById(R.id.spinnerItemType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.itemtypes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItemType.setAdapter(adapter);

        final EditText etItem = (EditText) findViewById(R.id.etItemName);
        final EditText etItemDesc = (EditText) findViewById(R.id.etItemDesc);
        final EditText etItemQuantity = (EditText) findViewById(R.id.etItemQuantity);
        final EditText etItemPrice = (EditText) findViewById(R.id.etItemPrice);
        etItemPrice.setFilters(new InputFilter[] {new MoneyValueFilter()});

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResult = new Intent();
                intentResult.putExtra("KEY_ITEM",
                        new Item(Item.ItemType.fromInt(spinnerItemType.getSelectedItemPosition()), etItem.getText().toString(),
                                etItemDesc.getText().toString(), Short.parseShort(etItemQuantity.getText().toString()),
                                Float.parseFloat(etItemPrice.getText().toString())));
                setResult(RESULT_OK, intentResult);
                finish();
            }
        });
    }

}
