public class Person implements FlightObserver{
    protected String _name;

    public Person(String name){
        this._name=name;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    @Override
    public void update(Flight flight) {
        System.out.println(this.getClass().getSimpleName()+" " + _name + " received flight update: " +
                "the take-off time of flight number " + flight.getFlightNumber() +
                "\n from: " + flight.getFrom()+
                "\n to: " +flight.getTo()+
                "\n will be " + flight.getTakeOff() +
                "\n Duration: " + flight.getFlightTime() +
                "\n Landing time: " + flight.getLanding());
    }

    @Override
    public void update(Flight flight,int oldPrice,int newPrice) {
        System.out.println(this.getClass().getSimpleName()+" " + _name + " received flight update: " +
                "flight number " + flight.getFlightNumber() +
                "\n old price: " + oldPrice+
                "\n new: " +newPrice);

    }

    @Override
    public void updateCancel(Flight flight) {
        System.out.println(this.getClass().getSimpleName()+" " + _name + " received flight update: " +
                "flight number " + flight.getFlightNumber() +" got canceled");

    }
}
