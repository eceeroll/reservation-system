public class Passenger {

    String birthDate;
    String TCKN;
    String firstName;
    String surName;

    public Passenger(String birthDate, String TCKN, String firstName, String surName) {
        this.birthDate = birthDate;
        this.TCKN = TCKN;
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getTCKN() {
        return TCKN;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }
}
