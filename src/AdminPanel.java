import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminPanel extends JFrame{
    private JTable firmalar;
    private JPanel flistele;
    private JButton geri;
    private JButton sil;
    private JCheckBox otobus;
    private JCheckBox tren;
    private JCheckBox ucak;
    private JTextField firmaAdi;
    private JButton ekleButon;
    private JLabel fAdi;
    private JLabel tasitlar;
    private JTextField hText;
    private JButton onay;
    private JLabel hBedeli;
    private JButton cikis;
    public ArrayList<Company> companyList;
    public int HIZMET_BEDELI;

    public AdminPanel(){
        setContentPane(flistele);
        setSize(600,300);
        setTitle("Admin Paneli");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        DefaultTableModel tbModel = new DefaultTableModel(new Object[]{"Firma Adi", "Arac Turu"}, 0);
        firmalar.setModel(tbModel);
        String[] sutunlar={"Firma Adi", "Arac Turu"};
        tbModel.addRow(sutunlar);

        IDAndPasswords idAndPasswords = new IDAndPasswords();
        companyList = idAndPasswords.getCompanies();

        for (Company company : companyList) {
            tbModel.addRow(new Object[]{company.getName(), company.getVehicles()});
        }

        ekleButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String companyName = firmaAdi.getText();
                ArrayList<VehicleType> selectedVehicleTypes = new ArrayList<>();

                if(companyName.equals("") || !otobus.isSelected() && !ucak.isSelected() && !tren.isSelected()){
                    JOptionPane.showMessageDialog(flistele, "Ilgili alanlari dogru bir sekilde doldurunuz!!!");

                } else {

                    if (otobus.isSelected()) {
                        selectedVehicleTypes.add(VehicleType.OTOBUS);
                    }
                    if (tren.isSelected()) {
                        selectedVehicleTypes.add(VehicleType.TREN);
                    }
                    if (ucak.isSelected()) {
                        selectedVehicleTypes.add(VehicleType.UCAK);
                    }
                    companyName = firmaAdi.getText();
                    Company newCompany = new Company(companyName, companyName + "123", companyName, selectedVehicleTypes, new ArrayList<Vehicle>());
                    companyList.add(newCompany);
                    idAndPasswords.loginInfo.put(newCompany.getUsername(),newCompany.password);

                    System.out.println("username: "+ newCompany.getUsername() + "password: " + newCompany.getPassword());

                    tbModel.addRow(new Object[]{newCompany.getName(), newCompany.getVehicles()});

                    firmaAdi.setText("");
                    otobus.setSelected(false);
                    tren.setSelected(false);
                    ucak.setSelected(false);
                    JOptionPane.showMessageDialog(null,"Firma basariyla olusturldu.Firma sifresi: "+newCompany.getPassword());
                }
            }
        });

        sil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firmalar.getSelectedColumnCount()==1){
                    companyList.remove(firmalar.getSelectedRow()-1);
                    tbModel.removeRow(firmalar.getSelectedRow());

                }else{
                    if(firmalar.getRowCount()==0){
                        JOptionPane.showMessageDialog(null,"silinecek firma yok");
                    }else{
                        JOptionPane.showMessageDialog(null,"Lutfen tek bir satir seciniz.");
                    }
                }
            }

        });
        onay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HIZMET_BEDELI=Integer.parseInt(hText.getText());
                hText.setText("");
                System.out.println(HIZMET_BEDELI);
            }
        });

        cikis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage(idAndPasswords.loginInfo,companyList);
                loginPage.setVisible(true);
                setVisible(false);
            }
        });
    }
}