import java.util.ArrayList;
import java.util.List;

public class Flight {
    private String flightNumber;
    private String from;
    private String to;
    private Time takeOff;
    private Time landing;
    private int flightTime;
    private List<FlightObserver> observers;
    private  int price;

    public Flight(String flightNumber,String from, String to, int takeOffDay, int takeOffMonth, int takeOffYear, int takeOffHour, int takeOffMinute, int flightTime,int price) {
        this.flightNumber=flightNumber;
        this.from = from;
        this.to = to;
        this.takeOff = new Time(takeOffDay, takeOffMonth, takeOffYear, takeOffHour, takeOffMinute);
        this.flightTime = flightTime;
        this.landing = takeOff.calculateLandingTime(flightTime / 60, flightTime % 60);
        this.observers = new ArrayList<>();
        this.price=price;
    }
    public int getPrice(){
        return  this.getPrice();
    }

    public void setPrice(int price){
        int oldPrice=this.price;
        this.price=price;
        notifyObserversPrice(oldPrice);

    }

    private void notifyObserversPrice(int oldPrice) {
        for (FlightObserver observer : observers) {
            observer.update(this,oldPrice,this.price);
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
        notifyObservers();
    }

    // Getter and setter for 'to' field
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
        notifyObservers();
    }

    public void registerObserver(FlightObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(FlightObserver observer) {
        observers.remove(observer);
    }


    private void notifyObservers() {
        for (FlightObserver observer : observers) {
            observer.update(this);
        }
    }

    public void setTakeOff(int day, int month, int year, int hour, int minute) {
        this.takeOff = new Time(day, month, year, hour, minute);
        this.landing = takeOff.calculateLandingTime(flightTime / 60, flightTime % 60);
        notifyObservers();
    }


    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
        this.landing = takeOff.calculateLandingTime(flightTime / 60, flightTime % 60);
        notifyObservers();
    }

    public void cancelFlight(){
        for (FlightObserver observer : observers) {
            observer.updateCancel(this);
        }
    }

    public String getFlightNumber() {
        return  flightNumber;
    }

    public String getTakeOff() {
        return takeOff.toString();
    }

    public String getFlightTime() {
        return flightTime/60+":"+flightTime%60;
    }

    public String getLanding() {
        return landing.toString();
    }

    public static void main(String[] args) {
        // Creating a flight
        Flight flight = new Flight("121","New York", "London", 4, 10, 2024, 10, 20, 123,1000);

        // Creating passengers
        Passenger passenger1 = new Passenger("Alice");
        Passenger passenger2 = new Passenger("Bob");

        // Registering passengers for the flight
        flight.registerObserver(passenger1);
        flight.registerObserver(passenger2);

        // Simulating an update in flight details
        flight.setFlightTime(25);

        // Unregistering a passenger
        flight.unregisterObserver(passenger2);

        // Simulating another update in flight details
        flight.setTakeOff(2, 4, 2024, 12, 30);
        flight.setPrice(123);
    }
}
