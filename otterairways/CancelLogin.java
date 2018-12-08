package edu.csumb.james.otterairways;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class CancelLogin extends Activity implements View.OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel_login);

        View createButton = findViewById(R.id.Ok_button);
        createButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Ok_button) {
            EditText cinput = (EditText) findViewById(R.id.accountName_edittext);
            EditText cinput2 = (EditText) findViewById(R.id.accountPassword_edittext);
            String name = cinput.getText().toString();
            String pw = cinput2.getText().toString();
            boolean found = false;

            DatabaseHelper db = new DatabaseHelper(this);
            boolean res = db.getUsernameCheck(name , pw);

                   if(!res) {
                        ArrayList<Reservation> reservations = db.getAllReservations();
                        ArrayList<Reservation> reservationTransfer = new ArrayList<Reservation>();

                        for (Reservation reservation : reservations) {
                            if (reservation.getName().equals(name)) {
                                reservationTransfer.add(reservation);
                                found = true;
                            }
                        }

                        if (!found) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                            // set title
                            alertDialogBuilder.setTitle("Sorry");

                            // set dialog message
                            alertDialogBuilder
                                    .setMessage("There is no reservation under this account!")
                                    .setCancelable(false)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // if this button is clicked, close
                                            // current activity
                                            CancelLogin.this.finish();
                                            Intent i = new Intent(CancelLogin.this, MainActivity.class);
                                            startActivity(i);
                                        }
                                    });


                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();

                        }
                        else {
                            Intent i = new Intent(this, Cancel.class);
                            Bundle extraInfo = new Bundle();
                            extraInfo.putString("name", name);
                            i.putExtras(extraInfo);
                            startActivity(i);
                        }
                    }
                    else
                   {
                       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                       // set title
                       alertDialogBuilder.setTitle("Sorry");

                       // set dialog message
                       alertDialogBuilder
                               .setMessage("Invalid username or password!")
                               .setCancelable(false)
                               .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialog, int id) {
                                       // if this button is clicked, close
                                       // current activity
                                       CancelLogin.this.finish();
                                       Intent i = new Intent(CancelLogin.this, MainActivity.class);
                                       startActivity(i);
                                   }
                               });


                       // create alert dialog
                       AlertDialog alertDialog = alertDialogBuilder.create();

                       // show it
                       alertDialog.show();
                   }
                }
            }
        }

