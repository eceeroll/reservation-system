import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Objects;

public class LoginPage extends JFrame {

    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JPanel panel1;
    private JLabel messageLabel;

    private HashMap<String, String> loginInfo;
    private ArrayList<Company> companyList;

    // constructor
    public LoginPage(HashMap<String, String> originalLoginInfo, ArrayList<Company> companyList) {
        this.loginInfo = originalLoginInfo;
        this.companyList = companyList;

        add(panel1);
        setSize(400, 400);
        setTitle("Giriş Formu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Login Button Click event listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == loginButton) {
                    String username = usernameField.getText();
                    String password = String.valueOf(passwordField1.getPassword());

                    if (loginInfo.containsKey(username)) {
                        // IF USER IS AN ADMIN
                        if (Objects.equals(loginInfo.get(username), password) && "ece".equals(username)) {
                            // login is successful as an ADMIN
                            AdminPanel adminPanel = new AdminPanel();
                            adminPanel.setVisible(true);
                            dispose();
                            System.out.println("Giriş Başarılı!");
                            System.out.println("Kullanıcı: " + username);
                        } else {
                            // Determine the logged-in company
                            Company loggedInCompany = getLoggedInCompany(username);

                            if (loggedInCompany != null) {
                                // login is successful as a COMPANY
                                CompanyPanel companyPanel = new CompanyPanel(loggedInCompany);
                                companyPanel.setVisible(true);
                                dispose();
                                System.out.println("Giriş Başarılı!");
                                System.out.println("Kullanıcı: " + username);
                            } else {
                                // User is not associated with any company
                                // Handle the situation accordingly
                            }
                        }
                    }
                }
            }
        });
    }

    // Giriş yapan kullanıcının ait olduğu şirketi belirle
    private Company getLoggedInCompany(String username) {
        for (Company company : companyList) {
            if (company.getUsername().equals(username)) {
                return company;
            }
        }
        return null; // Kullanıcı bulunamazsa null dönebilirsiniz.
    }
}
