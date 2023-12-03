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
                redirectToTicketPage();
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

//    Bilet Sorgula ya tıklandıktan sonra yapılacak işlemler:
    private void redirectToTicketPage() {
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
                //  ücret hesaplaması
                int price = calculatePrice(trip.getRoute(), departure, arrival);

                String tripInfo = "Seferi Yapan Firma: " + trip.getCompany().getName() +
                        "\n" + departure + " - " + arrival + "\n" +
                        "Sefer Ücreti: " + price + "TL" + "\n";

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

    private void showSeatSelection(Trip selectedTrip) {
        JFrame ticketFrame = new JFrame("Koltuk Seçimi Yap");
        ticketFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ticketFrame.setSize(500, 300);

        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(new GridLayout(6, 4)); // Adjust the grid layout based on the number of seats

        int koltukSayisi = selectedTrip.getVehicle().getKoltukSayisi(); // Get the number of seats

        for (int i = 1; i <= koltukSayisi; i++) {
            JCheckBox koltukCheckBox = new JCheckBox(Integer.toString(i));
            if (selectedSeats.contains(i)) {
                koltukCheckBox.setEnabled(false); // Disable already selected seats
            }
            ticketPanel.add(koltukCheckBox);

            // Add action listener to track selected seats
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

        // Add a submit button to confirm the seat selection
        JButton submitButton = new JButton("Devam Et");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the seat selection confirmation
                ArrayList<Integer> selectedSeats = new ArrayList<>();
                Component[] components = ticketPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) component;
                        if (checkBox.isSelected()) {
                            selectedSeats.add(Integer.valueOf(String.valueOf(Integer.parseInt(checkBox.getText()))));
                        }
                    }
                }

                System.out.println("Seçilen Koltuklar: " + selectedSeats);

                // Close the seat selection window
                ticketFrame.dispose();
            }
        });
        ticketPanel.add(submitButton);

        // Add the panel to the user interface
        ticketFrame.getContentPane().add(BorderLayout.CENTER, ticketPanel);
        ticketFrame.setVisible(true);
    }

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

    private int calculatePrice(Route route, Cities departure, Cities arrival) {
        TransportationType transportationType = route.getYolTuru();
        return TripPrices.getUcret(transportationType, departure, arrival);
    }

//    Kullanıcının girdiği gün ile sefer gününü karşılaştırmak için
    private boolean isTravelDateMatch(Trip trip, int travelDate) {
        return trip.getTravelDate() == travelDate;
    }
}
