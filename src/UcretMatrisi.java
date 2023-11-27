//
//public class UcretMatrisi {
//    private static int[][] ucretMatrisi = {
//            // Karayolu için ücretler
//            {0, 50, 300, 150, 300},
//            {50, 0, 400, 100, 250},
//            {300, 400, 0, 0, 0},
//            {150, 100, 0, 0, 150},
//            {300,250, 0, 150, 0},
//            // Demiryolu için ücretler
//            {0, 50, 150, 250, 200, 300},
//            {50, 0, 50, 200, 100, 250},
//            {150, 50, 0, 150, 50, 200},
//            {250, 200, 150, 0, 100, 0},
//            {200, 100, 50, 100, 0, 150},
//            {0, 0, 0, 0, 0, 0}
//    };
//
//    public static int getUcret(City baslangicCity, City hedefCity, YolTuru yolTuru) {
//        int baslangicIndex = baslangicCity.ordinal();
//        int hedefIndex = hedefCity.ordinal();
//        int yolTuruIndex = yolTuru.ordinal();
//
//        return ucretMatrisi[baslangicIndex + yolTuruIndex * City.values().length][hedefIndex];
//    }
//
//    public static void main(String[] args) {
//        int istanbuldanKocaeliyeKarayoluUcreti = getUcret(City.ISTANBUL, City.KOCAELI, YolTuru.KARAYOLU);
////        System.out.println("Istanbul'dan Kocaeli'ye Karayolu Ucreti: " + istanbuldanKocaeliyeKarayoluUcreti);
//    }
//}
//
