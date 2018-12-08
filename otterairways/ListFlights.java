package edu.csumb.james.otterairways;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ListFlights extends AppCompatActivity implements View.OnClickListener, Serializable {
    private ArrayList<String> name;
    private ArrayList<String> time;
    private int tickets = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listflights);

        name = getIntent().getExtras().getStringArrayList("name");
        time = getIntent().getExtras().getStringArrayList("time");
        Bundle extras = getIntent().getExtras();
        tickets = extras.getInt("tickets");
        String flights = "";

        for (int ii = 0; ii < name.size(); ii++) {
            flights += "Flight no. "+name.get(ii) + " Departure time " + time.get(ii) + ".\n";
        }

        TextView showFlights = (TextView)findViewById(R.id.flights_textview);
        showFlights.setText(flights);

        View createButton = findViewById(R.id.ok_button);
        createButton.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ok_button)
        {
            EditText flig = (EditText)findViewById(R.id.flights_edittext);
            String flightNo = flig.getText().toString();

            for(String flight : name)
            {
                if(flight.equals(flightNo))
                {
                    Intent i = new Intent(this, Login.class);
                    Bundle extraInfo = new Bundle();
                    extraInfo.putInt("tickets", tickets);
                    extraInfo.putString("flightNumber", flightNo);
                    i.putExtras(extraInfo);
                    startActivity(i);
                }
            }
        }
    }
}
