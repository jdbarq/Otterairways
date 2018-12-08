package edu.csumb.james.otterairways;




import java.io.Serializable;

/**
 * flight class for managing flight plans.
 */

public class flight implements Serializable {
    private int id;
    private String flight;
    private String departure;
    private String arrival;
    private String time;
    private int capacity;
    private double price;

    public flight()
    {
    }

    public flight(String flight, String departure, String arrival, String time, int capacity, double price)
    {
        this.flight = flight;
        this.departure = departure;
        this.arrival = arrival;
        this.time = time;
        this.capacity = capacity;
        this.price = price;
    }

    public int getId()
    {
        return id;
    };

    public String getFlight()
            {
                return flight;
            }

    public String getDeparture()

    {
        return departure;

    }

    public String getArrival()
    {
        return  arrival;

    }

    public String getTime()
    {
        return time;

    }

    public int getCapacity()
    {
        return  capacity;

    }

    public double getPrice()
    {
        return  price;

    }

    public void setId(int id)
    {

        this.id = id;

    }

    public void setFlightNo(String flight)

    {
        this.flight = flight;

    }

    public void setDeparture(String departure)

    {

        this.departure = departure;

    }

    public void setArrival(String arrival)

    {
        this.arrival = arrival;

    }

    public void setTime(String time)

    {
        this.time =time;

    }

    public void setCapacity(int capacity)

    {
        this.capacity = capacity;

    }

    public void setPrice(double price)

    {
        this.price = price;

    }

    public String toString()

    {
        return "Plane [flight=" + flight + "]";
    }
}
