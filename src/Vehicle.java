public abstract class Vehicle {

    int aracNo;
    int koltukSayisi;
    FuelType yakitTuru;
    Company company;
    String aracIsmi;

    public Vehicle(int aracNo, int koltukSayisi, FuelType yakitTuru, Company company, String aracIsmi ){
        this.aracNo = aracNo;
        this.koltukSayisi = koltukSayisi;
        this.yakitTuru = yakitTuru;
        this.company = company;
        this.aracIsmi = aracIsmi;
    }

    public Company getCompany() {
        return company;
    }

    public String getAracIsmi(){
        return aracIsmi;
    }

    public int getAracNo() {
        return aracNo;
    }

    public void setAracNo(int aracNo) {
        this.aracNo = aracNo;
    }

    public int getKoltukSayisi() {
        return koltukSayisi;
    }

    public void setKoltukSayisi(int koltukSayisi) {
        this.koltukSayisi = koltukSayisi;
    }

    public FuelType getYakitTuru() {
        return yakitTuru;
    }

    //    Bus bus1 = new Bus(1, 45);
//    Train train1 = new Train(1,120);
//    Airplane airplane1 = new Airplane(1, 150);

}
