import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



// TODO:
// Sefer listelerken İki kere yazdırma sorununu çöz.
// Firma bilgilerini görüntüle arayüzünü yap.
// Araç eklerken Tren otomatik Elektrik, Uçak otomatik Gaz olacak. Aksi halde uyarı yazdır.
// Güzergah seçerken her araç uygun güzergah seçmelidir. Aksi takdirde, uyarı yazdır.
// Araç ve Sefer silme işlevlerini ekle. +
// Admin paneline Hizmet belirleme ekle. +
// Firma oluştururken şifreniz: diye mesaj gösterir.
// Yakıt Türü ve Araç Türü için enum oluştur.

public class CompanyPanel extends JFrame {
    private ArrayList<Vehicle> aracListesi;
    private ArrayList<Trip> seferListesi;
    private Company loggedInCompany;

    public CompanyPanel(Company loggedInCompany) {

        System.out.println("Giriş yapılan firma:" + loggedInCompany.getName());

        aracListesi = new ArrayList<>();

        setTitle("Firma Paneli");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton firmaBilgileriGoruntuleButton = new JButton("Firma Bilgilerini Görüntüle");
        panel.add(firmaBilgileriGoruntuleButton);

        JButton aracEkleButton = new JButton("Araç Ekle");
        panel.add(aracEkleButton);

        JButton aracListeleButton = new JButton("Araçları Listele");
        panel.add(aracListeleButton);

        JButton seferOlusturButton = new JButton("Yeni Sefer Oluştur");
        panel.add(seferOlusturButton);

        JButton seferListeleButton = new JButton("Seferleri Listele");
        panel.add(seferListeleButton);

        aracEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAracEkleForm(loggedInCompany);
            }
        });
        aracListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAracListeleForm();
            }
        });
        firmaBilgileriGoruntuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Firma Bilgileri: ");
                System.out.println("Firma Adı: " + loggedInCompany.getName());
                System.out.println("Firmanın Kullandığı Araç Türleri:");
                for(Vehicle vehicle : loggedInCompany.getCompanyVehicles()){
                    System.out.println(vehicle.getClass().getSimpleName());
                }
                System.out.println("Firmaya Ait Araçlar:");
                for(Vehicle vehicle : loggedInCompany.getCompanyVehicles()){
                    System.out.println(vehicle.getAracIsmi());
                }
            }
        });
        seferOlusturButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSeferOlusturForm(loggedInCompany);
            }
        });
        seferListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSeferListeleForm(loggedInCompany);
            }
        });
        add(panel);
    }

    private void showAracEkleForm(Company company) {
        JFrame aracEkleFrame = new JFrame("Araç Ekle");
        aracEkleFrame.setSize(300, 200);
        aracEkleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aracEkleFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel aracNoLabel = new JLabel("Araç No:");
        JTextField aracNoField = new JTextField();

        JLabel koltukSayisiLabel = new JLabel("Koltuk Sayısı:");
        JTextField koltukSayisiField = new JTextField();

        JLabel aracTurLabel = new JLabel("Araç Türü:");
        String[] aracTurOptions = {"Otobüs", "Tren", "Uçak"};

        JComboBox<String> aracTurComboBox = new JComboBox<>(aracTurOptions);

        JLabel yakitTuruLabel = new JLabel("Yakıt Türü");
        String[] yakitTuruOptions = {FuelType.BENZIN.toString(), FuelType.MOTORIN.toString(), FuelType.ELEKTRIK.toString(), FuelType.GAZ.toString()};

        JComboBox<String> yakitTuruComboBox = new JComboBox<>(yakitTuruOptions);

        JButton aracEkleKaydetButton = new JButton("Kaydet");

        panel.add(koltukSayisiLabel);
        panel.add(koltukSayisiField);
        panel.add(aracNoLabel);
        panel.add(aracNoField);
        panel.add(aracTurLabel);
        panel.add(aracTurComboBox);
        panel.add(yakitTuruLabel);
        panel.add(yakitTuruComboBox);
        panel.add(new JLabel("")); // Boşluk eklemek için
        panel.add(aracEkleKaydetButton);

//        ARAÇ EKLEME İŞLEMİ :
        aracEkleKaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int aracNo = Integer.parseInt(aracNoField.getText());
                    int koltukSayisi = Integer.parseInt(koltukSayisiField.getText());
                    String aracTur = (String) aracTurComboBox.getSelectedItem();
                    FuelType yakitTur = FuelType.valueOf(((String) yakitTuruComboBox.getSelectedItem()).toUpperCase());

                    Vehicle yeniArac = null;
                    switch (aracTur) {
                        case "Otobüs":
                            yeniArac = new Bus(aracNo, koltukSayisi, yakitTur, company, "Otobüs " + aracNo );
                            break;
                        case "Tren":
                            yeniArac = new Train(aracNo, koltukSayisi,yakitTur, company, "Tren " + aracNo);
                            break;
                        case "Uçak":
                            yeniArac = new Airplane(aracNo, koltukSayisi,yakitTur, company, "Uçak " + aracNo);
                            break;
                        default:
                            break;
                    }
                    if (yeniArac != null) {
                        aracListesi.add(yeniArac);
//                      // Add the new vehicle to the company's vehicle list
                        company.getCompanyVehicles().add(yeniArac);
                        System.out.println("Added vehicle to company: " + company.getName());
                        JOptionPane.showMessageDialog(aracEkleFrame, "Araç başarıyla eklendi!");
                    } else {
                        JOptionPane.showMessageDialog(aracEkleFrame, "Geçersiz araç türü!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(aracEkleFrame, "Araç No ve Koltuk Sayısı sayı olmalıdır!");
                }
                aracEkleFrame.dispose();
            }
        });

        aracEkleFrame.add(panel);
        aracEkleFrame.setVisible(true);
    }
    private void showAracListeleForm(){
        JFrame aracListeleFrame = new JFrame("Araç Listesi");
        aracListeleFrame.setSize(600, 400);
        aracListeleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aracListeleFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        String[] columnNames = {"Araç No", "Araç Türü", "Koltuk Sayısı", "Yakıt Türü"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Vehicle arac : aracListesi) {
            String aracTur = arac.getClass().getSimpleName();
            model.addRow(new Object[]{arac.getAracNo(), aracTur, arac.getKoltukSayisi(), arac.getYakitTuru()});
        }

        JTable aracTable = new JTable(model);
        JScrollPane  scrollPane = new JScrollPane(aracTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        aracListeleFrame.add(panel);
        aracListeleFrame.setVisible(true);
    }
    private void showSeferOlusturForm(Company company){
        JFrame seferOlusturFrame = new JFrame("Sefer Oluştur");
        seferOlusturFrame.setSize(600, 400);
        seferOlusturFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seferOlusturFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

//        ARAÇ SEÇİMİ:
        ArrayList<Vehicle> companyVehicles = company.getCompanyVehicles();

        JComboBox<String> vehiclesComboBox = new JComboBox<>();
        JLabel vehicleSelectionLabel = new JLabel("Araç Seçiniz:");

        for(Vehicle vehicle : companyVehicles){
            vehiclesComboBox.addItem(vehicle.getAracIsmi());
        }

        panel.add(vehicleSelectionLabel);
        panel.add(vehiclesComboBox);

//        GÜN SEÇİMİ:
        int[] tripDays = {4,5,6,7,8,9,10};
        JComboBox<Integer> daysComboBox = new JComboBox<>();
        JLabel daySelectionLabel = new JLabel("Gün Seçiniz:");

        for(int day : tripDays){
            daysComboBox.addItem(day);
        }

        panel.add(daySelectionLabel);
        panel.add(daysComboBox);

        //  GÜZERGAH SEÇİMİ:
        ArrayList<Route> allRoutes = new ArrayList<>();
        allRoutes = Route.getAllRoutes();

        JComboBox<String> routesComboBox = new JComboBox<>();
        JLabel routeSelectionLabel = new JLabel("Güzergah Swçiniz:");

        for(Route route : allRoutes){
            routesComboBox.addItem(route.getRouteName());
        }

        panel.add(routeSelectionLabel);
        panel.add(routesComboBox);

        JButton seferOlusturKaydetButton = new JButton("Sefer Oluştur");
        panel.add(new JLabel("")); // Boşluk eklemek için
        panel.add(seferOlusturKaydetButton);

//        SEFER OLUŞTURMA KAYDETME İŞLEMİ:
        seferOlusturKaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedVehicleName = (String) vehiclesComboBox.getSelectedItem();
                int selectedDay = (int) daysComboBox.getSelectedItem();
                String selectedRouteName = (String) routesComboBox.getSelectedItem();

                // Vehicle ve Route objeleri
                Vehicle selectedVehicle = findVehicleByName(selectedVehicleName, company);
                Route selectedRoute = findRouteByName(selectedRouteName);

                // Sefer objesi oluşturma işlemi:
                int tripId = generateRandomId();
                Trip newTrip = new Trip(tripId, company, selectedVehicle, selectedRoute, selectedDay, 0);

                // Bilgileri gösteren bir mesaj oluşturuyoruz.
                String message = "Sefer Bilgileri\n" +
                        "------------------------\n" +
                        "Sefer ID: " + newTrip.getTripId() + "\n" +
                        "Firma: " + newTrip.getCompany().getName() + "\n" +
                        "Araç: " + newTrip.getVehicle().getAracIsmi() + "\n" +
                        "Güzergah: " + newTrip.getRoute().getRouteName() + "\n" +
                        "Kalkış Günü: " + newTrip.getDepartureDate() + "\n" +
                        "------------------------\n" +
                        "Onaylıyor musunuz?";

                int confirm = JOptionPane.showConfirmDialog(seferOlusturFrame, message, "Sefer Bilgileri Onay", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Yeni seferi allTrips listesine ekle
                    Trip.getAllTrips().add(newTrip);

                    // Ve aynı zamanda, seferi oluşturan firmanın companyTrips listesine de ekle
                    company.addTripToCompany(newTrip);

                    // Bilgi mesajı göster
                    JOptionPane.showMessageDialog(seferOlusturFrame, "Sefer başarıyla oluşturuldu!");

                    // Pencereyi kapat
                    seferOlusturFrame.dispose();
                }
            }
        });

        seferOlusturFrame.add(panel);
        seferOlusturFrame.setVisible(true);

    }
    private void showSeferListeleForm(Company company){
        JFrame seferListeleFrame = new JFrame("Mevcut Seferler");
        seferListeleFrame.setSize(600, 400);
        seferListeleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seferListeleFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        String[] columnNames = {"Sefer ID", "Araç", "Güzergah", "Kalkış Tarihi"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

//        seferListesi.clear();

//        şirkete ait seferleri alıyoruzç
        seferListesi = company.getCompanyTrips();

        for(Trip trip : seferListesi){
            model.addRow(new Object[]{trip.getTripId(), trip.getVehicle().getAracIsmi(), trip.getRoute().getRouteName(), trip.getDepartureDate() + " Aralık 2023"});
        }

        JTable seferTable = new JTable(model);
        JScrollPane  scrollPane = new JScrollPane(seferTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        seferListeleFrame.add(panel);
        seferListeleFrame.setVisible(true);
    }

// İsme göre Vehicle ve Route objelerini bulan methodlar burda tanımla.
private Vehicle findVehicleByName(String vehicleName, Company company) {
    for (Vehicle vehicle : company.getCompanyVehicles()) {
        if (vehicle.getAracIsmi().equals(vehicleName)) {
            return vehicle;
        }
    }
    return null;
}
private Route findRouteByName(String routeName) {
        for (Route route : Route.getAllRoutes()) {
            if (route.getRouteName().equals(routeName)) {
                return route;
            }
        }
        return null; // Handle the case when the route is not found
    }
//    Random ID üreten method tanımla. ( sefer oluşturma işlemi için )
private int generateRandomId() {
    return (int) (Math.random() * 1000) + 1;
}}