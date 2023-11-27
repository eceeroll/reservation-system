import java.util.ArrayList;

public class Company extends User {

    private String companyName;
    private ArrayList<String> vehicles;

    public Company(String username, String password, String companyName, ArrayList<String> vehicles) {
        super(username, password);
        this.companyName = companyName;
        this.vehicles = vehicles;
    }

    public String getName() {
        return companyName;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }
}
