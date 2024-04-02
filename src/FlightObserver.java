public interface FlightObserver {


    void update(Flight flight);


    void update(Flight flight, int oldPrice, int newPrice);

    void updateCancel(Flight flight);
}
