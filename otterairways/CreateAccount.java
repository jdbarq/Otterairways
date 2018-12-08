package edu.csumb.james.otterairways;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.regex.*;



public class CreateAccount extends Activity implements OnClickListener{


    DatabaseHelper myDb;
    private TextView username;
    private EditText username1;
    private TextView password;
    private EditText password1;
    private Button createuserButton;
    private Button createCancelButton;
    private String match;
    private  int counter =0;

    public  ArrayList<String> usernameList = new ArrayList<String>();

    private int  id=1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);
        username = (TextView) findViewById(R.id.createaccountText);
        username1 = (EditText) findViewById(R.id.createaccountEdit);
        password = (TextView) findViewById(R.id.createaccountpasswordText);
        password1 = (EditText) findViewById(R.id.createaccountpasswordEdit);
        createuserButton = (Button) findViewById(R.id.createuserButton) ;
        createCancelButton   = (Button) findViewById(R.id.cancelCreateButton);
        createuserButton.setOnClickListener(this);
        createCancelButton.setOnClickListener(this);
        myDb = new DatabaseHelper(this);
        match = "^(?=.*[0-9])(?=.*[a-z-A-Z]{3}).*$";

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createuserButton: {
                boolean res = myDb.getUsername(username1.getText().toString());
                if(res) {
                    if (validate(username1.getText().toString()) && validate(password1.getText().toString())) {
                        boolean isInserted = myDb.insertData(username1.getText().toString(), password1.getText().toString());
                        if (isInserted = true) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                            // set title
                            alertDialogBuilder.setTitle("Congratulations");

                            // set dialog message
                            alertDialogBuilder
                                    .setMessage("Account has been created succesfully for "+username1.getText().toString())
                                    .setCancelable(false)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // if this button is clicked, close
                                            // current activity
                                            CreateAccount.this.finish();
                                        }
                                    });


                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();

                        }
                    }
                    else {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                        // set title
                        alertDialogBuilder.setTitle("Sorry");

                        // set dialog message
                        alertDialogBuilder
                                .setMessage(" Failed to make account! ")
                                .setCancelable(false)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if(counter==2){
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                        }
                                        // if this button is clicked, close
                                        // current activity
                                        //CreateAccount.this.finish();
                                    }
                                });


                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();
                        counter++;

                    }
                }
                else {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                    // set title
                    alertDialogBuilder.setTitle("Sorry");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Username already exists!")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if(counter==2){
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });


                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                }
            }
            break;

            case R.id.cancelCreateButton: {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            }
        }

    }

    protected boolean validate(String word){
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }
}


