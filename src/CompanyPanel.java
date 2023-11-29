import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CompanyPanel extends JFrame {
    private ArrayList<Vehicle> aracListesi;
    private Company loggedInCompany;

    public CompanyPanel(Company loggedInCompany) {

        System.out.println(loggedInCompany.getName());

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
        String[] yakitTuruOptions = {"Benzin", "Motorin", "Elektrik","Gaz"};

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
                    String yakitTur = (String) yakitTuruComboBox.getSelectedItem();

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

//        GÜZERGAH SEÇİMİ:
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
                int tripDay = Integer.parseInt(daySelectionLabel.getText());
//                Vehicle ı obje olarak al.
//                Route ı obje olarak al.
            }
        });

        seferOlusturFrame.add(panel);
        seferOlusturFrame.setVisible(true);

    }

//    İsme göre Vehicle ve Route objelerini bulan methodlar burda tanımla.

//    Random ID üreten method tanımla.


}