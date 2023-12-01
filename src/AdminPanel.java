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
    public ArrayList<Company> companyList;



    public AdminPanel(){

        setContentPane(flistele);
        setSize(400,300);
        setTitle("Listeleme sayfasi");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        DefaultTableModel tbModel = new DefaultTableModel(new Object[]{"Firma Adi", "Arac Turu"}, 0);
        firmalar.setModel(tbModel);
        String[] sutunlar={"Firma Adi", "Arac Turu"};
        tbModel.addRow(sutunlar);

        IDAndPasswords idAndPasswords = new IDAndPasswords(); // Create an instance of the IDAndPasswords class
        companyList = idAndPasswords.getCompanies(); // Get the ArrayList of Company objects


        for (Company company : companyList) {
            tbModel.addRow(new Object[]{company.getName(), company.getVehicles()});
        }


        ekleButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String companyName = firmaAdi.getText();
                ArrayList<String> selectedVehicles = new ArrayList<>();



                if(companyName.equals("") || !otobus.isSelected() && !ucak.isSelected() && !tren.isSelected()){
                    JOptionPane.showMessageDialog(flistele, "Ilgili alanlari dogru bir sekilde doldurunuz!!!");

                } else {


                    if (otobus.isSelected()) {
                        selectedVehicles.add("Otobüs");
                    }
                    if (tren.isSelected()) {
                        selectedVehicles.add("Tren");
                    }
                    if (ucak.isSelected()) {
                        selectedVehicles.add("Uçak");
                    }
                    companyName = firmaAdi.getText();
                    Company newCompany = new Company(companyName, companyName + "123", companyName, selectedVehicles, new ArrayList<Vehicle>());

                    companyList.add(newCompany);


                    tbModel.addRow(new Object[]{newCompany.getName(), newCompany.getVehicles()});

                    firmaAdi.setText("");
                    otobus.setSelected(false);
                    tren.setSelected(false);
                    ucak.setSelected(false);


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
    }
}