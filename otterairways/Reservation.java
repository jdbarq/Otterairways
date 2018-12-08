package edu.csumb.james.otterairways;
import java.text.NumberFormat;


public class Reservation{
    private int id;
    private String name;
    private String flightNo;
    private String departure;
    private String arrival;
    private int tickets;
    private double price;
    private String time;

    public Reservation(){
    }

    public Reservation(String name, String flightNo, String departure, String arrival, int tickets, double price, String time){
        this.name = name;
        this.flightNo = flightNo;
        this.departure = departure;
        this.arrival = arrival;
        this.tickets = tickets;
        this.price = price;
        this.time = time;
    }
    public String getTime() {return time; }

    public String getName()
    {
        return name;
    }

    public int getId() {return id;};

    public String getFlightNo(){return flightNo;}

    public String getDeparture(){return departure;}

    public String getArrival(){return  arrival;}

    public int getTickets() {return  tickets;}

    public double getPrice() {return  price;}

    public void setTime(String time) {this.time = time;}

    public void setId(int id){this.id = id;}

    public void setName(String name)
    {
        this.name = name;
    }

    public void setFlightNo(String flightNo){this.flightNo = flightNo;}

    public void setDeparture(String departure) {this.departure = departure;}

    public void setArrival(String arrival) {this.arrival = arrival;}

    public void setTickets(int tickets) {this.tickets = tickets;}

    public void setPrice(double price) {this.price = price;}

    public String toString(){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        String moneyString = format.format(price);

        return "Username: " + name +
                "\nFlight number: " + flightNo +
                "\nDeparture: " + departure + ", " + time +
                "\nArrival: " + arrival +
                "\nNumber of tickets: " + tickets +
                "\nReservation number: " + id +
                "\nTotal Amount: " + moneyString +
                "\n";
    }
}
