package hu.ait.tiffanynguyen.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import hu.ait.tiffanynguyen.shoppinglist.data.Item;
import hu.ait.tiffanynguyen.shoppinglist.filter.MoneyValueFilter;


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
        final ImageView ivCreate = (ImageView) findViewById(R.id.ivCreate);
        final Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.send_arrow);
        etItemPrice.setFilters(new InputFilter[] {new MoneyValueFilter()});

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (etItem.getText().toString().matches(""))
                        throw new Exception();

                    final Intent intentResult = new Intent();
                    intentResult.putExtra("KEY_ITEM",
                            new Item(Item.ItemType.fromInt(spinnerItemType.getSelectedItemPosition()), etItem.getText().toString(),
                                    etItemDesc.getText().toString(), Integer.parseInt(etItemQuantity.getText().toString()),
                                    Float.parseFloat(etItemPrice.getText().toString())));

                    ivCreate.startAnimation(anim);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            setResult(RESULT_OK, intentResult);
                            finish();
                        }
                    }, 1200);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Name, quantity, and price must not be empty", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
