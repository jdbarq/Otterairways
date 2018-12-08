package edu.csumb.james.otterairways;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME= "airline.db";
    public static final String username_table= "username_table";
    public static final String user_id= "id";
    public static final String username= "username";
    public static final String password= "password";
    public static final String user_datetime= "datetime";


    public static final String flight_table= "flight_table";
    public static final String flight_id= "ID";
    public static final String flight_no= "flightno";
    public static final String departure= "departure";
    public static final String arrival= "arrival";
    public static final String departure_time= "departureTime";
    public static final String flight_capacity= "flightCapacity";
    public static final String price= "price";

    public static final String booking_table= "booking_table";
    public static final String tickets = "tickets";

    public static final String cancel_table = "cancel_table";
    public static final String time= "time";
    public static final String log_table ="log_table";




    public DatabaseHelper(Context context ){
        super(context , DATABASE_NAME, null ,1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + username_table + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT , password TEXT , datetime TEXT)");
        db.execSQL("create table " + flight_table + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, flightno TEXT , departure TEXT , arrival TEXT , departureTime TEXT , flightCapacity INTEGER , price REAL)");
        db.execSQL("create table " + booking_table + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT , flightno TEXT , departure TEXT , arrival TEXT , tickets INTEGER , price REAL , departureTime TEXT) ");
        db.execSQL("create table " + cancel_table + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT , flightno TEXT , time TEXT , departure TEXT , arrival TEXT , tickets INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ username_table);
        db.execSQL("DROP TABLE IF EXISTS "+ flight_table);
        db.execSQL("DROP TABLE IF EXISTS "+ booking_table);
        db.execSQL("DROP TABLE IF EXISTS "+ cancel_table);
        onCreate(db);
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public boolean insertData(String username1  , String password1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(username , username1);
        contentValues.put(password , password1);
        contentValues.put(user_datetime , getDateTime());
        long result = db.insert(username_table, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

      


    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ username_table, null);
        return res;
    }

    public Cursor getCancelData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from  cancel_table", null);
        return res;
    }

    public Cursor getBookingData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from  booking_table", null);
        return res;
    }

    public boolean getUsername(String username1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT "+username+" FROM "+username_table+" WHERE "+username+"='"+username1+"'", null);
        if(res.getCount() == 0){
            return true;
        }
        else
            return false;
    }

    public boolean getUsername1(String username1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT "+username+" FROM "+username_table+" WHERE "+username+"='"+username1+"'", null);
        if(res.getCount() == 0){
            return false;
        }
        else
            return true;
    }

    public boolean getUsernameCheck(String username1 , String password1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT username , password from username_table where username=? AND password=?", new String[]{username1,password1});
        if(res.getCount() == 0){
            return true;
        }
        else
            return false;
    }
    public boolean getFlightNo(String flight){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM "+flight_table+" WHERE "+flight_no+"='"+flight+"'", null);
        if(res.getCount() == 0){
            return true;
        }
        else
            return false;
    }

    public flight getFlight(String flightNum){
        String query = "SELECT  * FROM flight_table ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        flight flight = null;
        if (cursor.moveToFirst()){
            do{
                flight = new flight();
                flight.setId(Integer.parseInt(cursor.getString(0)));
                flight.setFlightNo(cursor.getString(1));
                flight.setDeparture(cursor.getString(2));
                flight.setArrival(cursor.getString(3));
                flight.setTime(cursor.getString(4));
                flight.setCapacity(Integer.parseInt(cursor.getString(5)));
                flight.setPrice(Double.parseDouble(cursor.getString(6)));
                if (flight.getFlight().equals(flightNum))
                    return flight;
            } while (cursor.moveToNext());
        }
        // Log.d(TAG, "getAllPlanes() - " + planes.toString());
        cursor.close();
        db.close();
        return flight;
    }



    public boolean addFlight(String flighid1 , String departure1 , String arrival1 ,String departureTime1 , int capacity1, double price1 ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(flight_no, flighid1);
        contentValues.put(departure, departure1);
        contentValues.put(arrival, arrival1);
        contentValues.put(departure_time, departureTime1);
        contentValues.put(flight_capacity, capacity1);
        contentValues.put(price, price1);
        long result = db.insert(flight_table, null, contentValues);

         if(result == -1) {
             return false;
         }
             else
                 return true;
    }


    public void addReservation(Reservation reservation){


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(username, reservation.getName());
        values.put(flight_no, reservation.getFlightNo());
        values.put(departure, reservation.getDeparture());
        values.put(arrival, reservation.getArrival());
        values.put(tickets, reservation.getTickets());
        values.put(price, reservation.getPrice());
        values.put(departure_time, reservation.getTime());

        db.insert(booking_table, null, values);

        db.close();
    }

    public Cursor getFlights(String departurre , String arrival){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("Select * from flight_table where departure =? and arrival =? ", new String[]{departurre , arrival});
        return res;
    }

    public ArrayList<flight> getAllFlights(){
        ArrayList<flight> flights = new ArrayList<flight>();

        String query = "SELECT  * FROM flight_table";
        SQLiteDatabase Mydb = this.getReadableDatabase();
        Cursor cursor = Mydb.rawQuery(query, null);

        flight flight = null;
        if (cursor.moveToFirst()){
            do{
                flight = new flight();
                flight.setId(Integer.parseInt(cursor.getString(0)));
                flight.setFlightNo(cursor.getString(1));
                flight.setDeparture(cursor.getString(2));
                flight.setArrival(cursor.getString(3));
                flight.setTime(cursor.getString(4));
                flight.setCapacity(Integer.parseInt(cursor.getString(5)));
                flight.setPrice(Double.parseDouble(cursor.getString(6)));

                flights.add(flight);
            } while (cursor.moveToNext());
        }

        cursor.close();
        Mydb.close();
        return flights;
    }

    public Reservation getReservation(String name, String flightNo){
        String query = "SELECT * FROM " + booking_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Reservation reservation= null;
        if (cursor.moveToFirst()){
            do{
                reservation = new Reservation();
                reservation.setId(Integer.parseInt(cursor.getString(0)));
                reservation.setName(cursor.getString(1));
                reservation.setFlightNo(cursor.getString(2));
                reservation.setDeparture(cursor.getString(3));
                reservation.setArrival(cursor.getString(4));
                reservation.setTickets(Integer.parseInt(cursor.getString(5)));
                reservation.setPrice(Double.parseDouble(cursor.getString(6)));
                reservation.setTime(cursor.getString(7));
                if (reservation.getName().equals(name) && reservation.getFlightNo().equals(flightNo))
                    return reservation;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return reservation;
    }

    public ArrayList<Reservation> getAllReservations(){
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();

        String query = "SELECT  * FROM " + booking_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Reservation reservation= null;
        if (cursor.moveToFirst()){
            do{
                reservation = new Reservation();
                reservation.setId(Integer.parseInt(cursor.getString(0)));
                reservation.setName(cursor.getString(1));
                reservation.setFlightNo(cursor.getString(2));
                reservation.setDeparture(cursor.getString(3));
                reservation.setArrival(cursor.getString(4));
                reservation.setTickets(Integer.parseInt(cursor.getString(5)));
                reservation.setPrice(Double.parseDouble(cursor.getString(6)));
                reservation.setTime(cursor.getString(7));

                reservations.add(reservation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return reservations;
    }

    public Reservation getReservation(int id){
        String query = "SELECT  * FROM " + booking_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Reservation reservation= null;
        if (cursor.moveToFirst()){
            do{
                reservation = new Reservation();
                reservation.setId(Integer.parseInt(cursor.getString(0)));
                reservation.setName(cursor.getString(1));
                reservation.setFlightNo(cursor.getString(2));
                reservation.setDeparture(cursor.getString(3));
                reservation.setArrival(cursor.getString(4));
                reservation.setTickets(Integer.parseInt(cursor.getString(5)));
                reservation.setPrice(Double.parseDouble(cursor.getString(6)));
                reservation.setTime(cursor.getString(7));
                if (reservation.getId() == id)
                    return reservation;
            } while (cursor.moveToNext());
        }
        // Log.d(TAG, "getAllPlanes() - " + planes.toString());
        cursor.close();
        db.close();
        return reservation;
    }

    public void deleteReservation(int id , String username1 , String flight , String departure1 , String arrival1 , int ticket1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(flight_no, flight);
        contentValues.put(username, username1);
        contentValues.put(time , getDateTime());
        contentValues.put(departure , departure1);
        contentValues.put(arrival , arrival1);
        contentValues.put(tickets , ticket1);
        db.insert(cancel_table, null, contentValues);
        db.execSQL("DELETE FROM " + booking_table + " WHERE " + user_id + " = " + id);
        db.close();
    }



}
