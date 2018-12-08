package edu.csumb.james.otterairways;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CancelConfirm extends Activity implements View.OnClickListener{
    private int id;
    private String flight;
    private String username;
    private String departure;
    private String arrival;
    private int ticket;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel_confirm);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        flight = extras.getString("name");
        username = extras.getString("flightNo");
        departure = extras.getString("departure");
        arrival = extras.getString("arrival");
        ticket = extras.getInt("ticket");


        View yesButton = findViewById(R.id.yes_button);
        yesButton.setOnClickListener(this);

        View noButton = findViewById(R.id.no_button);
        noButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.yes_button)
        {
            DatabaseHelper db = new DatabaseHelper(this);
            Reservation reservation = db.getReservation(id);


            db.deleteReservation(id ,username ,flight , departure , arrival , ticket);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set title
            alertDialogBuilder.setTitle("Done!");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Booking has been cancelled Succesfully")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            dialog.cancel();
                            Intent i = new Intent(CancelConfirm.this, MainActivity.class);
                            startActivity(i);
                        }
                    });


            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }
        else if(v.getId() == R.id.no_button)
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set title
            alertDialogBuilder.setTitle("Alert!");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Booking has not been cancelled ")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            dialog.cancel();
                            Intent i = new Intent(CancelConfirm.this, MainActivity.class);
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
