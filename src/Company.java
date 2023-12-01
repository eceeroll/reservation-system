import java.util.ArrayList;

public class Company extends User {

    private String companyName;
    private ArrayList<String> vehicles;
    private ArrayList<Vehicle> companyVehicles;

    //  şirkete ait seferleri tutan bir arraylist oluşturuyoruz.
    private ArrayList<Trip> companyTrips = new ArrayList<>();

    public Company(String username, String password, String companyName, ArrayList<String> vehicles, ArrayList<Vehicle> companyVehicles) {
        super(username, password);
        this.companyName = companyName;
        this.vehicles = vehicles;
        this.companyVehicles = companyVehicles;
    }

//    sefer ekleme methodu:
    public void addTripToCompany(Trip trip){
        companyTrips.add(trip);
    }
    public String getName() {
        return companyName;
    }
    public ArrayList<String> getVehicles() {
        return vehicles;
    }
    public ArrayList<Vehicle> getCompanyVehicles(){
        return companyVehicles;
    }
    public ArrayList<Trip> getCompanyTrips(){
        return companyTrips;
    }
}
