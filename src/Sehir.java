public class Sehir {

    private String name;
    private int karayoluUcret;
    private int demiryoluUcret;
    private int havayoluUcret;

    public Sehir(String name, int karayoluUcret, int demiryoluUcret, int havayoluUcret) {
        this.name = name;
        this.karayoluUcret = karayoluUcret;
        this.demiryoluUcret = demiryoluUcret;
        this.havayoluUcret = havayoluUcret;
    }

//    Getters and Setters

    public String getIsim() {
        return name;
    }

    public void setIsim(String name) {
        this.name = name;
    }

    public int getKarayoluUcreti() {
        return karayoluUcret;
    }

    public void setKarayoluUcreti(int karayoluUcret) {
        this.karayoluUcret = karayoluUcret;
    }

    public int getDemiryoluUcreti() {
        return demiryoluUcret;
    }

    public void setDemiryoluUcreti(int demiryoluUcret) {
        this.demiryoluUcret = demiryoluUcret;
    }

    public int getHavayoluUcreti() {
        return havayoluUcret;
    }

    public void setHavayoluUcreti(int havayoluUcret) {
        this.havayoluUcret = havayoluUcret;
    }

}

