import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Trip {

//    Bütün firmalara ait bütün seferleri tutan bir ArrayList tanımlıyoruz.
private static ArrayList<Trip> allTrips = new ArrayList<>();

    private int trip_id;
    Company company;
    Vehicle vehicle;
    Route route;
    private int departureDate; // tarih gün olarak tutulacak. ( 4-10 aralık )
    private int price;

    public Trip(int trip_id, Company company, Vehicle vehicle, Route route, int departureDate, int price) {
        this.trip_id = trip_id; // random sayı ataması
        this.company = company;
        this.vehicle = vehicle;
        this.route = route;
        this.departureDate = departureDate;
        this.price = price;

        // Ve aynı zamanda, seferi oluşturan firmanın companyTrips listesine de eklenir
        if (company != null) {
            company.addTripToCompany(this);
        }
    }

    public static void addTripToAllTrips(Trip trip) {
        allTrips.add(trip);
    }

    public static ArrayList<Trip> getAllTrips() {
        return allTrips;
    }

//    Getters and Setters

    public int getTripId() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(int departureDate) {
        this.departureDate = departureDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
