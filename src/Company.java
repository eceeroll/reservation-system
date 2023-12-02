import java.util.ArrayList;

public class Company extends User {

    private String companyName;
    private ArrayList<VehicleType> vehicleTypes;
    private ArrayList<Vehicle> companyVehicles;

    //  şirkete ait seferleri tutan bir arraylist oluşturuyoruz.
    private ArrayList<Trip> companyTrips = new ArrayList<>();

    public Company(String username, String password, String companyName, ArrayList<VehicleType> vehicleTypes, ArrayList<Vehicle> companyVehicles) {
        super(username, password);
        this.companyName = companyName;
        this.vehicleTypes = vehicleTypes;
        this.companyVehicles = companyVehicles;
    }

    //  sefer ekleme methodu:
    public void addTripToCompany(Trip trip){
        companyTrips.add(trip);
    }
    public String getName() {
        return companyName;
    }
    public ArrayList<VehicleType> getVehicles() {
        return vehicleTypes;
    }
    public ArrayList<Vehicle> getCompanyVehicles(){
        return companyVehicles;
    }
    public ArrayList<Trip> getCompanyTrips(){
        return companyTrips;
    }
}
