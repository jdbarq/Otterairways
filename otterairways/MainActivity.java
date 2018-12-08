package edu.csumb.james.otterairways;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends Activity implements OnClickListener{


    private Button createButton;
    private Button manageButton;
    private Button reserveButton;
    private Button cancelButton;
    //Toast create = Toast.makeText(this, "onCreate method", Toast.LENGTH_SHORT);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createButton = (Button) findViewById(R.id.create);
        manageButton = (Button) findViewById(R.id.manage);
        reserveButton = (Button) findViewById(R.id.reserve);
        cancelButton = (Button) findViewById(R.id.cancel);


        createButton.setOnClickListener(this);
        manageButton.setOnClickListener(this);
        reserveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    public void displayMsg(View v){
        String message = "Manage System is under development";
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:
                Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(intent);
                break;
            case R.id.manage:
                Intent intentt = new Intent(getApplicationContext(), ManageSystem.class);
                startActivity(intentt);
                break;
            case R.id.reserve:
                Intent intenttt = new Intent(getApplicationContext(), ReserveSeat.class);
                startActivity(intenttt);
                break;
            case R.id.cancel:
                Intent intentttt = new Intent(getApplicationContext(), CancelLogin.class);
                startActivity(intentttt);
                break;
        }
    }


}
