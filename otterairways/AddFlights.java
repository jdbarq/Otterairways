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

public class AddFlights extends Activity implements OnClickListener {


    DatabaseHelper myDb;
    private EditText flight;
    private EditText departure;
    private EditText arrival;
    private EditText departureTime;
    private EditText flightCapacity;
    private EditText price;
    private Button addButton;
    StringBuffer buffer = new StringBuffer();
    private int id = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addflights);
        flight = (EditText) findViewById(R.id.flightId);
        departure = (EditText) findViewById(R.id.departureId);
        arrival = (EditText) findViewById(R.id.arrivalId);
        departureTime = (EditText) findViewById(R.id.departureTimeId);
        flightCapacity = (EditText) findViewById(R.id.capacityId);
        price = (EditText) findViewById(R.id.priceId);
        addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(this);
        myDb = new DatabaseHelper(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addButton: {
                buffer.append("Flight No" + flight.getText().toString()+"\n");
                buffer.append("Departure " + departure.getText().toString()+"\n");
                buffer.append("arrival " + arrival.getText().toString()+"\n");
                buffer.append("departure time " + departureTime.getText().toString()+"\n");
                buffer.append("flight capacity" + flightCapacity.getText().toString()+"\n");
                buffer.append(" price " + price.getText().toString()+"\n");
                boolean isInserted;
                boolean res = myDb.getFlightNo(flight.getText().toString());
                if (res) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    // set title
                    alertDialogBuilder.setTitle("Confirm");
                    // set dialog message
                    alertDialogBuilder

                            .setMessage(buffer.toString())
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    String flightCap = flightCapacity.getText().toString();
                                    int fcapacity = Integer.parseInt(flightCap);
                                    double pric = Double.parseDouble(price.getText().toString());
                                    // if this button is clicked, close
                                     boolean isInserted = myDb.addFlight(flight.getText().toString(), departure.getText().toString(), arrival.getText().toString(), departureTime.getText().toString(), fcapacity, pric);
                                    if (isInserted = true) {
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddFlights.this);
                                        // set title
                                        alertDialogBuilder.setTitle("Congratulations");
                                        // set dialog message
                                        alertDialogBuilder
                                                .setMessage("flight has been added succesfully")
                                                .setCancelable(false)
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        // if this button is clicked, close
                                                        // current activity
                                                        AddFlights.this.finish();
                                                    }
                                                });
                                        // create alert dialog
                                        AlertDialog alertDialog = alertDialogBuilder.create();
                                        // show it
                                        alertDialog.show();
                                    }
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // show it
                    alertDialog.show();



                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    // set title
                    alertDialogBuilder.setTitle("Sorry");
                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Failed to add flight")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // show it
                    alertDialog.show();
                }
                break;
            }
        }
    }
}
