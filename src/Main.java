import javax.swing.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

//        Şehir bilgilerini HashMap e kaydediyoruz.
        HashMap<String, Sehir> sehirler = new HashMap<>();

        sehirler.put("İstanbul", new Sehir("İstanbul", 50, 100, 300));
        sehirler.put("Kocaeli", new Sehir("Kocaeli", 100, 200, 400));
        sehirler.put("Ankara", new Sehir("Ankara", 300, 400, -1));
        sehirler.put("Eskişehir", new Sehir("Eskişehir", 150, 100, -1));
        sehirler.put("Konya", new Sehir("Konya", 300, 250, -1));

        String sehir = "İstanbul";
        Sehir sehirBilgileri = sehirler.get(sehir);

        System.out.println(sehirBilgileri.getIsim() + " şehrinin ücretleri:");
        System.out.println("Karayolu: " + sehirBilgileri.getKarayoluUcreti());
        System.out.println("Demiryolu: " + sehirBilgileri.getDemiryoluUcreti());
        System.out.println("Havayolu: " + sehirBilgileri.getHavayoluUcreti());


//        Admin admin =  Admin.getAdminInfo();
//        String adminUsername = admin.getUsername();
        IDAndPasswords idAndPasswords = new IDAndPasswords();
//        Send the hashmap to loginPage
        LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo());

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
                LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo());
                loginPage.setVisible(true);
            }
        });
    }
}