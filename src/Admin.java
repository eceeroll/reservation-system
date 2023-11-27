public class Admin extends User{

    private static final String ADMIN_USERNAME = "Ece";
    private static final String ADMIN_PASSWORD = "Ece0104";
    private static Admin admin;

    public Admin(String username, String password) {
        super(username, password);
    }

    public static Admin getAdminInfo(){
    if(admin == null){
        admin = new Admin(ADMIN_USERNAME, ADMIN_PASSWORD);
    }
        return admin;
    }
}
