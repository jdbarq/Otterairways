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
import java.util.ArrayList;
import android.widget.Toast;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ReserveSeat extends Activity implements OnClickListener {


    DatabaseHelper myDb;
    private String departure;
    private String arrival;
    private int tickets;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserveseat);

        View createButton = findViewById(R.id.reserve);
        createButton.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reserve: {

                EditText inputDeparture = (EditText)findViewById(R.id.departure);
                departure = inputDeparture.getText().toString();
                EditText inputArrival = (EditText)findViewById(R.id.arrival);
                arrival = inputArrival.getText().toString();
                EditText inputTicket = (EditText)findViewById(R.id.numberoftickets);
                tickets = Integer.parseInt(inputTicket.getText().toString());

                DatabaseHelper db = new DatabaseHelper(this);
                ArrayList<flight> list = db.getAllFlights();

                ArrayList<String> flightName = new ArrayList<String>();
                ArrayList<String> flightTime = new ArrayList<String>();

                boolean check = false;
                for(flight flight : list)
                {
                    if(flight.getDeparture().equals(departure))
                    {
                        if(flight.getArrival().equals(arrival))
                        {
                            if(flight.getCapacity() > tickets)
                            {
                                flightName.add(flight.getFlight());
                                flightTime.add(flight.getTime());
                                check = true;
                            }
                        }
                    }
                }
                if (tickets > 7) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                    // set title
                    alertDialogBuilder.setTitle("Sorry");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Reservation cannot be made due to system restriction ")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    //CreateAccount.this.finish();
                                }
                            });


                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                    inputTicket.setText("");
                }

                    else if(check) {
                    Intent i = new Intent(this, ListFlights.class);
                    i.putStringArrayListExtra("name", flightName);
                    i.putStringArrayListExtra("time", flightTime);
                    Bundle extraInfo = new Bundle();
                    extraInfo.putInt("tickets", tickets);
                    i.putExtras(extraInfo);
                    startActivity(i);
                }
                else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                    // set title
                    alertDialogBuilder.setTitle("Sorry");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("No Flights available at this time ")
                            .setCancelable(false)
                            .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ReserveSeat.this.finish();
                                    Intent i = new Intent(ReserveSeat.this, MainActivity.class);
                                    startActivity(i);

                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
                break;
            }
        }
    }
    public void showMessage1(String title , String Message){
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}




































