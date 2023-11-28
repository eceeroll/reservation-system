import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class CompanyPanel extends JFrame {
    private ArrayList<Vehicle> aracListesi;
    private Company loggedInCompany;


    public CompanyPanel(Company loggedInCompany) {
        aracListesi = new ArrayList<>();
        setTitle("Firma Paneli");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton aracEkleButton = new JButton("Araç Ekle");
        panel.add(aracEkleButton);

        JButton aracListeleButton = new JButton("Araçları Listele");
        panel.add(aracListeleButton);

        aracEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAracEkleForm();
            }
        });
        aracListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAracListeleForm();
            }
        });

        add(panel);
    }

    private void showAracEkleForm() {
        JFrame aracEkleFrame = new JFrame("Araç Ekle");
        aracEkleFrame.setSize(300, 200);
        aracEkleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aracEkleFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

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

        JButton kaydetButton = new JButton("Kaydet");

        panel.add(koltukSayisiField);
        panel.add(koltukSayisiLabel);
        panel.add(aracNoLabel);
        panel.add(aracNoField);
        panel.add(aracTurLabel);
        panel.add(aracTurComboBox);
        panel.add(yakitTuruLabel);
        panel.add(yakitTuruComboBox);
        panel.add(new JLabel("")); // Boşluk eklemek için
        panel.add(kaydetButton);

        kaydetButton.addActionListener(new ActionListener() {
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
                            yeniArac = new Bus(aracNo, koltukSayisi, yakitTur, loggedInCompany);
                            break;
                        case "Tren":
                            yeniArac = new Train(aracNo, koltukSayisi,yakitTur, loggedInCompany);
                            break;
                        case "Uçak":
                            yeniArac = new Airplane(aracNo, koltukSayisi,yakitTur, loggedInCompany);
                            break;
                        default:
                            break;
                    }
                    if (yeniArac != null) {
                        aracListesi.add(yeniArac);
                        JOptionPane.showMessageDialog(aracEkleFrame, "Araç başarıyla eklendi!");
                        System.out.println("Eklenen Araç : " + yeniArac.getCompany());
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
}