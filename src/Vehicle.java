public abstract class Vehicle {

    int aracNo;
    int koltukSayisi;

    public Vehicle(int aracNo, int koltukSayisi){
        this.aracNo = aracNo;
        this.koltukSayisi = koltukSayisi;
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

//    Bus bus1 = new Bus(1, 45);
//    Train train1 = new Train(1,120);
//    Airplane airplane1 = new Airplane(1, 150);

}
