package edu.csumb.james.otterairways;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;



public class Cancel extends Activity implements View.OnClickListener, Serializable {

    private String name;
    private ArrayList<Reservation> reservationTransfer = new ArrayList<Reservation>();
    Reservation newReservation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");

        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        reservations = db.getAllReservations();

        for (Reservation key : reservations)
        {
            if (key.getName().equals(name))
            {
                newReservation = key;
                reservationTransfer.add(newReservation);
            }

        }

        String text = "";
        for (Reservation key : reservationTransfer)
        {
            newReservation = key;
            text += key.toString() + "\n";
        }

        TextView tv = (TextView)findViewById(R.id.textview);
        tv.setText(text);

        View createButton = findViewById(R.id.ok_button);
        createButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ok_button)
        {
            EditText cinput = (EditText)findViewById(R.id.edittext);
            String flightNo = cinput.getText().toString();

            for(Reservation key : reservationTransfer)
            {
                newReservation = key;
                if(newReservation.getFlightNo().equals(flightNo))
                {
                    int id = newReservation.getId();
                    String username = newReservation.getName();
                    String flight = newReservation.getFlightNo();
                    String dep = newReservation.getDeparture();
                    String arr = newReservation.getArrival();
                    int ticket = newReservation.getTickets();
                    Intent i = new Intent(this, CancelConfirm.class);
                    Bundle extraInfo = new Bundle();
                    extraInfo.putInt("id", id);
                    extraInfo.putString("name",username);
                    extraInfo.putString("flightNo", flight);
                    extraInfo.putString("departure", dep);
                    extraInfo.putString("arrival", arr);
                    extraInfo.putInt("ticket", ticket);
                    i.putExtras(extraInfo);
                    startActivity(i);
                }
            }
        }
    }
}
