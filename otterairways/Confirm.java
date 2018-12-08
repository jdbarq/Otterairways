package edu.csumb.james.otterairways;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class Confirm extends Activity implements View.OnClickListener {
    private String name;
    private String flightNo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        flightNo = extras.getString("flight");

        DatabaseHelper db = new DatabaseHelper(this);
        Reservation Reservation = db.getReservation(name, flightNo);

        TextView tv = (TextView)findViewById(R.id.edittext);
        tv.setText(Reservation.toString());

        View createButton = findViewById(R.id.Ok_button);
        createButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("Congratulations");

        // set dialog message
        alertDialogBuilder
                .setMessage("Reservation has been made succesfully")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        Confirm.this.finish();
                        Intent i = new Intent(Confirm.this, MainActivity.class);
                        startActivity(i);
                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }
}

