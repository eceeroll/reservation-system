import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompanyPanel extends JFrame {
    private ArrayList<Vehicle> aracListesi;

    public CompanyPanel() {
        aracListesi = new ArrayList<>();
        setTitle("Firma Paneli");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton aracEkleButton = new JButton("Araç Ekle");
        panel.add(aracEkleButton);

        aracEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAracEkleForm();
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

        JButton kaydetButton = new JButton("Kaydet");

        panel.add(koltukSayisiField);
        panel.add(koltukSayisiLabel);
        panel.add(aracNoLabel);
        panel.add(aracNoField);
        panel.add(aracTurLabel);
        panel.add(aracTurComboBox);
        panel.add(new JLabel("")); // Boşluk eklemek için
        panel.add(kaydetButton);

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int aracNo = Integer.parseInt(aracNoField.getText());
                    int koltukSayisi = Integer.parseInt(koltukSayisiField.getText());
                    String aracTur = (String) aracTurComboBox.getSelectedItem();

                    Vehicle yeniArac = null;

                    switch (aracTur) {
                        case "Otobüs":
                            yeniArac = new Bus(aracNo, koltukSayisi);
                            break;
                        case "Tren":
                            yeniArac = new Train(aracNo, koltukSayisi);
                            break;
                        case "Uçak":
                            yeniArac = new Airplane(aracNo, koltukSayisi);
                            break;
                        default:
                            break;
                    }

                    if (yeniArac != null) {
                        aracListesi.add(yeniArac);
                        JOptionPane.showMessageDialog(aracEkleFrame, "Araç başarıyla eklendi!");
                    } else {
                        JOptionPane.showMessageDialog(aracEkleFrame, "Geçersiz araç türü!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(aracEkleFrame, "Araç No ve Koltuk Sayısı sayı olmalıdır!");
                }

                aracEkleFrame.dispose(); // Formu kapat
            }
        });


        aracEkleFrame.add(panel);
        aracEkleFrame.setVisible(true);
    }

}
