import java.util.ArrayList;
import java.util.HashMap;
public class IDAndPasswords {

//    Store ID and Password info with HashMap
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    ArrayList<Company> companyList = new ArrayList<>();

    IDAndPasswords(){

        ArrayList<String> vehiclesCompanyA = new ArrayList<>();
        // Add elements to the ArrayList
        vehiclesCompanyA.add("Tren");

        //    Admin Info:
        Admin admin = new Admin("ece", "ece0104");
        //    Companies Info:
        Company companyA = new Company("Company_A", "A123", "A Taşımacılık", vehiclesCompanyA, new ArrayList<Vehicle>());
        Company companyB = new Company("Company_B", "B123","B Taşımacılık", vehiclesCompanyA, new ArrayList<Vehicle>());
        //    Company companyC = new Company("Company_C", "C123", "C Taşımacılık");

        companyList.add(companyA);
        companyList.add(companyB);

//        HashMap ' e kullanıcı bilgilerinin eklenmesi
        loginInfo.put(admin.getUsername(), admin.getPassword());
        loginInfo.put(companyA.getUsername(), companyA.getPassword());
        loginInfo.put(companyB.getUsername(), companyB.getPassword());
//        loginInfo.put(companyC.getUsername(), companyC.getPassword());

    }

    protected HashMap getLoginInfo(){
        return loginInfo;
    }

//    get all companies
    public ArrayList<Company> getCompanies() {
//        for (Company company : companyList) {
//            System.out.println("For döngüsü companylist: ");
//            System.out.println(company.getVehicles());
//        }
        return companyList;
    }

}
