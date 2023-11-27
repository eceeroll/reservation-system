import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage extends JFrame {

    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JPanel panel1;
    private JLabel messageLabel;

    HashMap <String, String> loginInfo = new HashMap<String,String>();

//    constructor
    LoginPage(HashMap<String,String> originalLoginInfo){

//        Create a copy of loginInfo
        loginInfo = originalLoginInfo;

        add(panel1);
        setSize(400,400);
        setTitle("Giriş Formu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//        Login Button Click event listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getSource() == loginButton){
                    String username = usernameField.getText();
                    String password = String.valueOf(passwordField1.getPassword());

                    if(loginInfo.containsKey(username)){
                        if(loginInfo.get(username).equals(password) && loginInfo.get(username) == "Ece"){
                    //   login is successfull as an ADMIN
                            AdminPanel adminPanel = new AdminPanel();
                            adminPanel.setVisible(true);
                            dispose();
                            System.out.println("Giriş Başarılı!");
                            System.out.println("Kullanıcı: " + username);
                        } else {
//                            messageLabel.setVisible(true);
//                            messageLabel.setForeground(Color.red);
//                            messageLabel.setText("Geçersiz Kullanıcı!");
                            CompanyPanel companyPanel = new CompanyPanel();
                            companyPanel.setVisible(true);
                            dispose();
                            System.out.println("Giriş Başarılı!");
                            System.out.println("Kullanıcı: " + username);
                        }
                    }
                }
            }
        });
    }
}
