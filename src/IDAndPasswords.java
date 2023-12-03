import java.util.ArrayList;
import java.util.HashMap;
public class IDAndPasswords {

//    Store ID and Password info with HashMap
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    ArrayList<Company> companyList = new ArrayList<>();

    IDAndPasswords(){

        ArrayList<VehicleType> vehiclesCompanyA = new ArrayList<>();
        ArrayList<VehicleType> vehiclesCompanyB = new ArrayList<>();
        ArrayList<VehicleType> vehiclesCompanyC = new ArrayList<>();
        ArrayList<VehicleType> vehiclesCompanyD = new ArrayList<>();
        ArrayList<VehicleType> vehiclesCompanyF = new ArrayList<>();
        // Add elements to the ArrayList
        vehiclesCompanyA.add(VehicleType.OTOBUS);
        vehiclesCompanyB.add(VehicleType.OTOBUS);
        vehiclesCompanyC.add(VehicleType.OTOBUS);
        vehiclesCompanyC.add(VehicleType.UCAK);
        vehiclesCompanyD.add(VehicleType.TREN);
        vehiclesCompanyF.add(VehicleType.UCAK);

        //    Admin Info:
        Admin admin = new Admin("ece", "ece0104");
        //    Companies Info:
        Company companyA = new Company("Company_A", "A123", "A Taşımacılık", vehiclesCompanyA, new ArrayList<Vehicle>());
        Company companyB = new Company("Company_B", "B123","B Taşımacılık", vehiclesCompanyA, new ArrayList<Vehicle>());
        Company companyC = new Company("Company_C","C123","C Tasimacilik",vehiclesCompanyC,new ArrayList<Vehicle>());
        Company companyD = new Company("Company_D","D123","D Tasimacilik",vehiclesCompanyD,new ArrayList<Vehicle>());
        Company companyF = new Company("Company_F","F123","F Tasimacilik",vehiclesCompanyF,new ArrayList<Vehicle>());


        companyList.add(companyA);
        companyList.add(companyB);
        companyList.add(companyC);
        companyList.add(companyD);
        companyList.add(companyF);

//        HashMap ' e kullanıcı bilgilerinin eklenmesi
        loginInfo.put(admin.getUsername(), admin.getPassword());
        loginInfo.put(companyA.getUsername(), companyA.getPassword());
        loginInfo.put(companyB.getUsername(), companyB.getPassword());
        loginInfo.put(companyC.getUsername(), companyC.getPassword());
        loginInfo.put(companyD.getUsername(), companyD.getPassword());
        loginInfo.put(companyF.getUsername(), companyF.getPassword());

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
