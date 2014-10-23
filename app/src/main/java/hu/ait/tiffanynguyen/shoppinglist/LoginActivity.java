package hu.ait.tiffanynguyen.shoppinglist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hu.ait.tiffanynguyen.shoppinglist.adapter.ItemAdapter;
import hu.ait.tiffanynguyen.shoppinglist.data.Item;


public class LoginActivity extends Activity {

    private final String PREF_NAME = "MyPassword";
    public static final int REQUEST_NEW_PASSWORD = 70;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences sp =
                getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if (sp.getBoolean("my_first_time", true)) {
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("New password")
                    .setMessage("Enter a new password please:")
                    .setView(input)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Editable value = input.getText();
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("password", value.toString());
                            editor.commit();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
            sp.edit().putBoolean("my_first_time", false).commit();
        }

        final EditText pwdFill = (EditText) findViewById(R.id.etLogin);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = sp.getString("password", null);
                if (pwd != null) {
                    if (pwd.equals(pwdFill.getText().toString())) {
                        Intent i = new Intent(LoginActivity.this, ItemsListActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Incorrect password. Try again", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Oops, something went wrong on our end. Please close the " +
                                    "app and try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, "Password changed", Toast.LENGTH_LONG).show();
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_new_pass) {
            Intent i = new Intent();
            i.setClass(this, ChangePasswordActivity.class);
            // REQUEST... is a request code to get results from a certain activity
            startActivityForResult(i, REQUEST_NEW_PASSWORD);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
