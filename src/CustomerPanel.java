import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;

public class CustomerPanel extends JFrame {

    private JFrame frame;
    private JPanel panel;
    private JComboBox<Cities> departureComboBox;
    private JComboBox<Cities> arrivalComboBox;
    private JComboBox<Integer> daysComboBox;
    private JComboBox<Integer> passengerComboBox;

    private HashSet<Integer> selectedSeats = new HashSet<>();

    public CustomerPanel() {
        frame = new JFrame("Müşteri Paneli");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

//        Bilet Sorgulamadan önce alınacak bilgiler:
        JLabel departureLabel = new JLabel("Kalkış Yeri:");
        departureComboBox = new JComboBox<>(Cities.values());
        JLabel arrivalLabel = new JLabel("Varış Yeri:");
        arrivalComboBox = new JComboBox<>(Cities.values());
        JLabel dateLabel = new JLabel("Yolculuk Tarihi:");
        Integer[] daysArray = {4, 5, 6, 7, 8, 9, 10};
        daysComboBox = new JComboBox<>(daysArray);
        JLabel passengerLabel = new JLabel("Yolcu Sayısı:");
        Integer[] numberOfPassengers = {1, 2, 3, 4, 5};
        passengerComboBox = new JComboBox<>(numberOfPassengers);

        JButton searchTicketButton = new JButton("Bilet Sorgula");
        searchTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTripSelection();
            }
        });

        panel.add(departureLabel);
        panel.add(departureComboBox);
        panel.add(arrivalLabel);
        panel.add(arrivalComboBox);
        panel.add(dateLabel);
        panel.add(daysComboBox);
        panel.add(passengerLabel);
        panel.add(passengerComboBox);
        panel.add(searchTicketButton, BorderLayout.CENTER);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }

//    Bilet Sorgula ya tıklandıktan sonra -> Sefer seçme sayfası
private void showTripSelection() {
        Cities departure = (Cities) departureComboBox.getSelectedItem();
        Cities arrival = (Cities) arrivalComboBox.getSelectedItem();
        int travelDate = (int) daysComboBox.getSelectedItem();
        int passengerCount = (int) passengerComboBox.getSelectedItem();

        ArrayList<Trip> matchingTrips = findMatchingTrips(departure, arrival, travelDate);

        if (matchingTrips.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Aramanızla eşleşen bir sefer bulunamadı!");
        } else {
            JFrame ticketFrame = new JFrame("Bilet Satın Al");
            ticketFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ticketFrame.setSize(500, 300);

            JPanel ticketPanel = new JPanel();
//            ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
            ticketPanel.setLayout(new GridLayout(6,2));

                //  Uygun seferleri bulup bilgilerini listeleme işlemi:
            for(Trip trip : matchingTrips){
                //  ücret hesaplaması ( 1 kişi için )
                int price = calculatePrice(trip.getRoute(), departure, arrival);

//                yolcu sayısına göre toplam ücret hesaplaması:
                int totalPrice = price * passengerCount;

                String tripInfo = "Seferi Yapan Firma: " + trip.getCompany().getName() +
                        "\n" + departure + " - " + arrival + "\n" +
                        "Sefer Ücreti ( 1 Kişi ): " + price + "TL" + "\n" +
                        passengerCount + " Kişi İçin Ücret: " + totalPrice + " TL";

                JTextArea tripTextArea = new JTextArea(tripInfo);
                tripTextArea.setEditable(false);

                JButton purchaseButton = new JButton("Satın Al");

//                Satın Al butonuna tıklandığında:
                purchaseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showSeatSelection(trip);
                    }
                });

                ticketPanel.add(tripTextArea);
                ticketPanel.add(purchaseButton);
            }
            JScrollPane scrollPane = new JScrollPane(ticketPanel);
            ticketFrame.getContentPane().add(BorderLayout.CENTER, scrollPane);
            ticketFrame.setVisible(true);
        }
    }

//    Sefer seçimi yapıldıktan sonra -> koltuk seçim ekranı
private void showSeatSelection(Trip selectedTrip) {
    JFrame ticketFrame = new JFrame("Koltuk Seçimi Yap");
    ticketFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ticketFrame.setSize(500, 300);

    JPanel ticketPanel = new JPanel();
//    kullanıcı tarafından girilen yolcu sayısı:
    int passengerCount = (int) passengerComboBox.getSelectedItem();
    ticketPanel.setLayout(new GridLayout(6, passengerCount));

    int koltukSayisi = selectedTrip.getVehicle().getKoltukSayisi();

    for (int i = 1; i <= koltukSayisi; i++) {
        JCheckBox koltukCheckBox = new JCheckBox(Integer.toString(i));
        if (selectedSeats.contains(i)) {
//            önceden seçilmiş olan koltukların checkbox unu disable yapıyoruz
            koltukCheckBox.setEnabled(false);
        }
        ticketPanel.add(koltukCheckBox);

        int finalI = i;
        koltukCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (koltukCheckBox.isSelected()) {
                    selectedSeats.add(finalI);
                } else {
                    selectedSeats.remove(finalI);
                }
            }
        });
    }

    JButton submitButton = new JButton("Devam Et");
    submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedSeats.size() == passengerCount) {
                ArrayList<Integer> selectedSeatsList = new ArrayList<>(selectedSeats);
                System.out.println("Seçilen Koltuklar: " + selectedSeatsList);

                showPassengerInfoForm(passengerCount);

                ticketFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(ticketFrame, "Yolcu sayısı ve Seçilen Koltuk Sayısı Eşit Olmalıdır!");
            }
        }
    });
    ticketPanel.add(submitButton);

    // Add the panel to the user interface
    ticketFrame.getContentPane().add(BorderLayout.CENTER, ticketPanel);
    ticketFrame.setVisible(true);
}

//    Koltuk seçimi yapıldıktan sonra -> yolcu bilgileri formu
private void showPassengerInfoForm(int passengerCount) {
    JFrame passengerInfoFrame = new JFrame("Yolcu Bilgileri Formu");
    passengerInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    passengerInfoFrame.setSize(600, 600);

    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel formTitleLabel = new JLabel("Yolcu Bilgileri");
    formTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    headerPanel.add(formTitleLabel);

    JPanel contentPanel = new JPanel(new GridLayout(passengerCount + 1, 2, 10, 10));
    contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//    Her yolcu için ayrı form oluşturuyoruz.
    for (int i = 1; i <= passengerCount; i++) {
        JLabel passengerLabel = new JLabel("Yolcu " + i + ":");
        passengerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        contentPanel.add(passengerLabel);

        JPanel passengerFieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        passengerFieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add individual passenger fields
        JLabel nameLabel = new JLabel("Ad:");
        JTextField nameField = new JTextField();
        JLabel surnameLabel = new JLabel("Soyad:");
        JTextField surnameField = new JTextField();
        JLabel birthDateLabel = new JLabel("Doğum Tarihi:");
        JTextField birthDateField = new JTextField();
        JLabel tcNoLabel = new JLabel("TC Kimlik No:");
        JTextField tcNoField = new JTextField();

        passengerFieldsPanel.add(nameLabel);
        passengerFieldsPanel.add(nameField);
        passengerFieldsPanel.add(surnameLabel);
        passengerFieldsPanel.add(surnameField);
        passengerFieldsPanel.add(birthDateLabel);
        passengerFieldsPanel.add(birthDateField);
        passengerFieldsPanel.add(tcNoLabel);
        passengerFieldsPanel.add(tcNoField);

        contentPanel.add(passengerFieldsPanel);
    }

    JPanel paymentButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton paymentButton = new JButton("Ödemeye Geç");

//    Ödemeye Geç butonuna tıklandığında yapılacak işlemler:
    paymentButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showPaymentForm();
        }
    });
    paymentButtonPanel.add(paymentButton);

    // Add components to the main panel
    mainPanel.add(headerPanel, BorderLayout.NORTH);
    mainPanel.add(contentPanel, BorderLayout.CENTER);
    mainPanel.add(paymentButtonPanel, BorderLayout.SOUTH);

    // Add the main panel to the frame and make it visible
    passengerInfoFrame.getContentPane().add(mainPanel);
    passengerInfoFrame.setVisible(true);
}

//   Yolcu bilgileri girildikten sonra -> ödeme sayfası
private void showPaymentForm() {

    JFrame paymentFrame = new JFrame("Ödeme Formu");
    paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    paymentFrame.setSize(400, 300);

    JPanel paymentPanel = new JPanel();
    paymentPanel.setLayout(new GridLayout(4,2));

    JLabel cardNumberLabel = new JLabel("Kart Numarası:");
    JTextField cardNumberField = new JTextField();

    JLabel nameLabel = new JLabel("Kart Üzerindeki Ad Soyad:");
    JTextField nameField = new JTextField();

    JLabel cvvLabel = new JLabel("CVV:");
    JTextField cvvField = new JTextField();

    JButton paymentButton = new JButton("Ödemeyi Tamamla");

    paymentButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cardNumber = cardNumberField.getText();
            String name = nameField.getText();
            String cvv = cvvField.getText();

//            Form geçerliliğini kontrol et.
            boolean validation = true;
            if (cardNumber.length() < 8) {
                validation = false;
                JOptionPane.showMessageDialog(paymentFrame, "Geçeriz Kart Numarası!");
            }

            if (!name.matches("[a-zA-Z\\s]+")) {
                validation = false;
                JOptionPane.showMessageDialog(paymentFrame, "Geçersiz Ad Soyad Bilgisi!");
            }

            if (!cvv.matches("\\d{3}")) {
                validation = false;
                JOptionPane.showMessageDialog(paymentFrame, "Geçersiz CVV!");
            }

            if (validation) {
                JOptionPane.showMessageDialog(paymentFrame, "Ödemeniz başarıyla alınmıştır!");

                paymentFrame.dispose();
            }
        }
    });

    paymentPanel.add(cardNumberLabel);
    paymentPanel.add(cardNumberField);
    paymentPanel.add(nameLabel);
    paymentPanel.add(nameField);
    paymentPanel.add(cvvLabel);
    paymentPanel.add(cvvField);
    paymentPanel.add(paymentButton);

    paymentFrame.getContentPane().add(BorderLayout.CENTER, paymentPanel);
    paymentFrame.setVisible(true);
}

//  Kullanıcının seçtiği koşullara uygun seferleri bulan method
    private ArrayList<Trip> findMatchingTrips(Cities departure, Cities arrival, int travelDate) {
        ArrayList<Trip> matchingTrips = new ArrayList<>();

        for (Trip trip : Trip.getAllTrips()) {
            Route route = trip.getRoute();

            if (route.getDepartureCity() == departure && route.getArrivalCity() == arrival) {
                if (isTravelDateMatch(trip, travelDate)) {
                    matchingTrips.add(trip);
                }
            } else {
                Cities[] stations = route.getStations();
                if (stations != null && stations.length > 0) {
                    if (isCityInStations(departure, arrival, stations) && isTravelDateMatch(trip, travelDate)) {
                        matchingTrips.add(trip);
                    }
                }
            }
        }

        return matchingTrips;
    }

//    Şehirler kalkış ve varış noktası haricindeki duraklardaysa
    private boolean isCityInStations(Cities departure, Cities arrival, Cities[] stations) {
        for (Cities station : stations) {
            if (station == departure || station == arrival) {
                return true;
            }
        }
        return false;
    }

//    Seçilen iki şehir ve güzergaha göre ücret hesaplama
    private int calculatePrice(Route route, Cities departure, Cities arrival) {
        TransportationType transportationType = route.getYolTuru();
        return TripPrices.getUcret(transportationType, departure, arrival);
    }

//    Kullanıcının girdiği gün ile sefer gününü karşılaştırmak için
    private boolean isTravelDateMatch(Trip trip, int travelDate) {
        return trip.getTravelDate() == travelDate;
    }
}
