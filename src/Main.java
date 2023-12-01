import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        TripPrices ulasim = new TripPrices();
        int istanbulKocaeliKarayolu = ulasim.getUcret(TransportationType.HAVAYOLU, Cities.ISTANBUL, Cities.KONYA);

        if (istanbulKocaeliKarayolu != -1) {
            System.out.println("Ulaşım Ücreti: " + istanbulKocaeliKarayolu);
        }

        IDAndPasswords idAndPasswords = new IDAndPasswords();
        ArrayList<Company> companyList = idAndPasswords.getCompanies();

//        Send the hashmap to loginPage
//        LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo(), companyList);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo(), companyList);
                loginPage.setVisible(true);
            }
        });
    }
}