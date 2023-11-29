
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminPanel extends JFrame {

    private JTextField companyNameField;
    private JCheckBox busCheckBox, trainCheckBox, planeCheckBox;
    private ArrayList<Company> companyList;
    private JPanel panel1;
    private JPanel panel2;

    public AdminPanel() {
        // Frame ayarları
        setTitle("Admin Panel");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Form elemanları
        JLabel nameLabel = new JLabel("Firma Adı:");
        companyNameField = new JTextField(20);

        JLabel vehiclesLabel = new JLabel("Kullanılan Taşıtlar:");
        busCheckBox = new JCheckBox("Otobüs");
        trainCheckBox = new JCheckBox("Tren");
        planeCheckBox = new JCheckBox("Uçak");

        JButton addButton = new JButton("Firma Ekle");

        addButton.addActionListener(e -> addCompany());

//        companyList = new ArrayList<>();
        IDAndPasswords idAndPasswords = new IDAndPasswords(); // Create an instance of the IDAndPasswords class
        companyList = idAndPasswords.getCompanies(); // Get the ArrayList of Company objects

        for (Company company : companyList) {
            System.out.println(company.getName());
        }

        // Main paneli oluşturuyoruz
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // JPanel'e tabloyu ekliyoruz
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        // Add the JTable to the panel1
        listCompanies();
        mainPanel.add(panel1, BorderLayout.CENTER);

        // Form panelini oluşturuyoruz
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2));
        formPanel.add(nameLabel);
        formPanel.add(companyNameField);
        formPanel.add(vehiclesLabel);
        formPanel.add(busCheckBox);
        formPanel.add(new JLabel());
        formPanel.add(trainCheckBox);
        formPanel.add(new JLabel());
        formPanel.add(planeCheckBox);
        formPanel.add(addButton);

        // Add the formPanel to the main panel at the WEST position
        mainPanel.add(formPanel, BorderLayout.WEST);

        // Add the main panel to the frame
        add(mainPanel);

        setVisible(true);

    }

    //    companyList güncelleme işlemi
    public void setCompanyList(ArrayList<Company> companyList) {
        this.companyList = companyList;
    }

    //    Şirket ekleme methodu
    private void addCompany() {

        // Firma adı ve taşıtları alma
        String companyName = companyNameField.getText();
        ArrayList<String> selectedVehicles = new ArrayList<>();

        if (busCheckBox.isSelected()) {
            selectedVehicles.add("Otobüs");
        }
        if (trainCheckBox.isSelected()) {
            selectedVehicles.add("Tren");
        }
        if (planeCheckBox.isSelected()) {
            selectedVehicles.add("Uçak");
        }

        Company newCompany = new Company(companyName, "123", companyName, selectedVehicles, new ArrayList<Vehicle>());

        companyList.add(newCompany);

        listCompanies();

        System.out.println("Ekleme işlemi başarılı! Güncellenen firmalar listesi: ");
        for (Company company : companyList) {
            System.out.println(company.getName());
        }

        // işlem başarılı:
        JOptionPane.showMessageDialog(this, "Yeni firma eklendi:\n" + newCompany.getName());

        // Form inputlarını eski haline getirme:
        companyNameField.setText("");
        busCheckBox.setSelected(false);
        trainCheckBox.setSelected(false);
        planeCheckBox.setSelected(false);
    }


    // VAR OLAN FİRMALARIN GÖRÜNTÜLENMESİ
private void listCompanies() {

    // Create a JTable to display the companies
    JTable table = new JTable();

    // Create a table model to populate the JTable
    DefaultTableModel tableModel = new DefaultTableModel(0, 2);
    tableModel.setColumnIdentifiers(new String[]{"Firma Adı", "Kullanılan Taşıtlar"});

    // Add each company to the table model
    for (Company company : companyList) {
        tableModel.addRow(new Object[]{company.getName(), company.getVehicles()});
    }

    // Set the table model to the JTable
    table.setModel(tableModel);

    // Add the JTable to a JScrollPane
    JScrollPane scrollPane = new JScrollPane(table);

    // Add the JScrollPane to the main panel

    panel1.add(scrollPane, BorderLayout.NORTH);
}

// FİRMA SİLİNME

//    fşrma silinme işlemi company list array listten yapıalcak.


}
