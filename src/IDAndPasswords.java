import java.util.ArrayList;
import java.util.HashMap;
public class IDAndPasswords {

//    Store ID and Password info with HashMap
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    ArrayList<Company> companyList = new ArrayList<>();

    IDAndPasswords(){
//        ARAÇ TÜRLERİNİ TUTAN LİSTELERİN OLUŞTURULMASI:
        ArrayList<VehicleType> vehiclesCompanyA = new ArrayList<>();
        ArrayList<VehicleType> vehiclesCompanyB = new ArrayList<>();
        ArrayList<VehicleType> vehiclesCompanyC = new ArrayList<>();
        ArrayList<VehicleType> vehiclesCompanyD = new ArrayList<>();
        ArrayList<VehicleType> vehiclesCompanyF = new ArrayList<>();
//        Kullanılan Araç türlerinin firmalara eklenmesi:
        vehiclesCompanyA.add(VehicleType.OTOBUS);
        vehiclesCompanyB.add(VehicleType.OTOBUS);
        vehiclesCompanyC.add(VehicleType.OTOBUS);
        vehiclesCompanyC.add(VehicleType.UCAK);
        vehiclesCompanyD.add(VehicleType.TREN);
        vehiclesCompanyF.add(VehicleType.UCAK);

        //    Admin Info:
        Admin admin = new Admin("ece", "ece0104");
        //    Şirketlerin oluşturulması :
        Company companyA = new Company("Company_A", "A123", "A Taşımacılık", vehiclesCompanyA, new ArrayList<Vehicle>());
        Company companyB = new Company("Company_B", "B123","B Taşımacılık", vehiclesCompanyA, new ArrayList<Vehicle>());
        Company companyC = new Company("Company_C","C123","C Tasimacilik",vehiclesCompanyC,new ArrayList<Vehicle>());
        Company companyD = new Company("Company_D","D123","D Tasimacilik",vehiclesCompanyD,new ArrayList<Vehicle>());
        Company companyF = new Company("Company_F","F123","F Tasimacilik",vehiclesCompanyF,new ArrayList<Vehicle>());

//        Araç nesnelerinin oluşturulması:
        Bus A_bus1 = new Bus(1, 20, FuelType.BENZIN, companyA, "Otobüs 1");
        Bus A_bus2 = new Bus(2, 15, FuelType.BENZIN, companyA, "Otobüs 2");
        Bus B_bus1 = new Bus(1, 15, FuelType.MOTORIN, companyB, "Otobüs 1");
        Bus B_bus2 = new Bus(2,20, FuelType.MOTORIN, companyB, "Otobüs 2");
        Bus C_bus1 = new Bus(1, 20, FuelType.MOTORIN, companyC, "Otobüs 1");
        Airplane C_airplane1 = new Airplane (1,30, FuelType.GAZ, companyC, "Uçak 1");
        Airplane C_airplane2 = new Airplane (2,30, FuelType.GAZ, companyC, "Uçak 2");
        Train D_train1 = new Train(1, 25, FuelType.ELEKTRIK, companyD, "Tren 1");
        Train D_train2 = new Train(2, 25, FuelType.ELEKTRIK, companyD, "Tren 2");
        Train D_train3 = new Train(3, 25, FuelType.ELEKTRIK, companyD, "Tren 3");
        Airplane F_airplane1 = new Airplane(1, 30, FuelType.GAZ, companyF, "Uçak 1");
        Airplane F_airplane2 = new Airplane(2, 30, FuelType.GAZ, companyF, "Uçak 2");

//        Şirketlere Araç eklenmesi :
        companyA.addVehicleToCompany(A_bus1);
        companyA.addVehicleToCompany(A_bus2);
        companyB.addVehicleToCompany(B_bus1);
        companyB.addVehicleToCompany(B_bus2);
        companyC.addVehicleToCompany(C_bus1);
        companyC.addVehicleToCompany(C_airplane1);
        companyC.addVehicleToCompany(C_airplane2);
        companyD.addVehicleToCompany(D_train1);
        companyD.addVehicleToCompany(D_train2);
        companyD.addVehicleToCompany(D_train3);
        companyF.addVehicleToCompany(F_airplane1);
        companyF.addVehicleToCompany(F_airplane2);

//        Bütün firmaların firma Arraylistine eklenmesi:
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
