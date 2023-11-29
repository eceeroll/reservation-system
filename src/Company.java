import java.util.ArrayList;

public class Company extends User {

    private String companyName;
    private ArrayList<String> vehicles;
    private ArrayList<Vehicle> companyVehicles;
//    private ArrayList<Trip> availableTrips;

    public Company(String username, String password, String companyName, ArrayList<String> vehicles, ArrayList<Vehicle> companyVehicles) {
        super(username, password);
        this.companyName = companyName;
        this.vehicles = vehicles;
        this.companyVehicles = companyVehicles;
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

}
