
package edu.csumb.james.otterairways;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;



public class Login extends Activity implements View.OnClickListener{

    DatabaseHelper myDb;
    private String flightNum = "";
    private int tickets = 0;
    private int numErrors = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Bundle extras = getIntent().getExtras();
        tickets = extras.getInt("tickets");
        flightNum = extras.getString("flightNumber");

        View createButton = findViewById(R.id.Ok_button);
        createButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Ok_button)
        {
            EditText username = (EditText)findViewById(R.id.accountName_edittext);
            EditText password = (EditText)findViewById(R.id.accountPassword_edittext);
            String name = username.getText().toString();
            String pw = password.getText().toString();
            boolean check = false;

            myDb = new DatabaseHelper(this);


            flight newPlane = myDb.getFlight(flightNum);
            boolean res = myDb.getUsername(name);
            if(!res){
                        check = true;
                        double price = tickets * newPlane.getPrice();
                        Reservation reservation = new Reservation(name, newPlane.getFlight(),
                                newPlane.getDeparture(), newPlane.getArrival(), tickets, price,
                                newPlane.getTime());

                        myDb.addReservation(reservation);
                        Intent i = new Intent(this, Confirm.class);
                        Bundle extraInfo = new Bundle();
                        extraInfo.putString("name", name);
                        extraInfo.putString("flight", flightNum);
                        i.putExtras(extraInfo);
                        startActivity(i);
                    }

            if(!check) {
                numErrors--;
                if (numErrors == 0) {
                    String text = "Username could not be found, please try again";
                    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
                    username.getText().clear();
                    password.getText().clear();
                } else {
                    numErrors = 1;
                    String text = "Could not find account from username(s) entered.";
                    Intent i = new Intent(this, ReserveSeat.class);
                    Bundle extraInfo = new Bundle();
                    extraInfo.putString("error", text);
                    i.putExtras(extraInfo);
                    startActivity(i);
                }
            }
        }
    }
}

