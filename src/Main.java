import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // selam ben sema
        // selam ben de ece
        TripPrices ulasim = new TripPrices();

        IDAndPasswords idAndPasswords = new IDAndPasswords();
        ArrayList<Company> companyList = idAndPasswords.getCompanies();

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
    }}