package edu.csumb.james.otterairways;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class ManageSystem extends Activity implements OnClickListener {

    DatabaseHelper myDb;
    private TextView username;
    private EditText username1;
    private TextView password;
    private EditText password1;
    private Button adminButton;
    private String admin = "admin2";
    StringBuffer buffer = new StringBuffer();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managesystem);
        username1 = (EditText) findViewById(R.id.adminUsername);
        password1 = (EditText) findViewById(R.id.adminPassword);
        adminButton = (Button) findViewById(R.id.adminButton);


        adminButton.setOnClickListener(this);
        myDb = new DatabaseHelper(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adminButton:
                if(username1.getText().toString().equals(admin) && password1.getText().toString().equals(admin)) {
                    Cursor res = myDb.getAllData();
                    Cursor resBooking = myDb.getBookingData();
                    Cursor resCancel = myDb.getCancelData();
                    if (res.getCount() == 0 && resBooking.getCount()==0 ) {
                        showMessage("Error", "No data found");
                        return;
                    } if(res.getCount()!=0) {
                        buffer.append("Transaction type :  New Account " + "\n");
                        while (res.moveToNext()) {

                            buffer.append("Username : " + res.getString(1) + "\n");

                            buffer.append("Datetime : " + res.getString(3) + "\n");
                        }
                        //buffer.delete(0 ,buffer.length());
                    }
                    if(resBooking.getCount()!=0){
                        buffer.append(" "+"\n");
                        buffer.append("Transaction type :  Bookings " + "\n");
                        while (resBooking.moveToNext()) {

                            buffer.append("Username : " + resBooking.getString(1) + "\n");

                            buffer.append("Flight No : " + resBooking.getString(2) + "\n");

                            buffer.append("Tickets : " + resBooking.getString(5) + "\n");
                            buffer.append(" Price  : " + resBooking.getString(6) + "\n");

                        }
                        //buffer.delete(0 ,buffer.length());
                    }
                    if(resCancel.getCount()!=0){

                        buffer.append(" "+"\n");
                        buffer.append("Transaction type :  Cancellations " + "\n");
                        while (resCancel.moveToNext()){
                            buffer.append("Username : " + resCancel.getString(1) + "\n");
                            buffer.append("Flight No : " + resCancel.getString(2) + "\n");
                            buffer.append(" Time : " + resCancel.getString(3) + "\n");
                            buffer.append(" Departure : " + resCancel.getString(4) + "\n");
                            buffer.append(" Arrival : " + resCancel.getString(5) + "\n");
                            buffer.append(" Tickets : " + resCancel.getString(6) + "\n");
                        }

                    }

                    //showMessage("Transactions", buffer.toString());
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage(buffer.toString());
                    builder1.setCancelable(true);
                    builder1.setNeutralButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    buffer.delete(0 ,buffer.length());
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                    builder1.setPositiveButton(
                            "Add Flights",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    buffer.delete(0 ,buffer.length());
                                    Intent intent = new Intent(getApplicationContext(), AddFlights.class);
                                    startActivity(intent);

                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                }
                else
                {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("Invalid username or password");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
        }

    }

    public void showMessage(String title , String Message){
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
