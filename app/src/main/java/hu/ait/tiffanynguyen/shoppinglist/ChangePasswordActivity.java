package hu.ait.tiffanynguyen.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hu.ait.tiffanynguyen.shoppinglist.adapter.ItemAdapter;
import hu.ait.tiffanynguyen.shoppinglist.data.Item;


public class ChangePasswordActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final EditText etPass = (EditText) findViewById(R.id.etNewPass);

        Button btnSave = (Button) findViewById(R.id.btnNewPass);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intentResult = new Intent();
                    if (etPass.getText().toString().matches(""))
                        throw new Exception();

                    SharedPreferences sp = getSharedPreferences("MyPassword", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("password", etPass.getText().toString());
                    editor.commit();
                    intentResult.putExtra("KEY_PASS", etPass.getText().toString());
                    setResult(RESULT_OK, intentResult);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
