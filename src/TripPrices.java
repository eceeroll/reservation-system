import java.util.HashMap;
import java.util.Map;

public class TripPrices {

    private static Map<TransportationType, Map<Cities, Map<Cities, Integer>>> ulasimTablosu;

    public TripPrices() {
        ulasimTablosu = new HashMap<>();

        // Karayolu ücretleri
        Map<Cities, Map<Cities, Integer>> karayolu = new HashMap<>();
        karayolu.put(Cities.ISTANBUL, Map.of(Cities.KOCAELI, 50, Cities.ANKARA, 300, Cities.ESKISEHIR, 150, Cities.KONYA, 300));
        karayolu.put(Cities.KOCAELI, Map.of(Cities.ISTANBUL, 50, Cities.ANKARA, 400, Cities.ESKISEHIR, 100, Cities.KONYA, 250));
        karayolu.put(Cities.ANKARA, Map.of(Cities.ISTANBUL, 300, Cities.KOCAELI, 400));
        karayolu.put(Cities.ESKISEHIR, Map.of(Cities.ISTANBUL, 150, Cities.KOCAELI, 100, Cities.KONYA, 150));
        karayolu.put(Cities.KONYA, Map.of(Cities.ISTANBUL, 300, Cities.KOCAELI, 250, Cities.ESKISEHIR, 150));

        // Demiryolu ücretleri
        Map<Cities, Map<Cities, Integer>> demiryolu = new HashMap<>();
        demiryolu.put(Cities.ISTANBUL, Map.of(Cities.KOCAELI, 50, Cities.BILECIK, 150, Cities.ANKARA, 250, Cities.ESKISEHIR, 200, Cities.KONYA, 300));
        demiryolu.put(Cities.KOCAELI, Map.of(Cities.ISTANBUL, 50, Cities.BILECIK, 50, Cities.ANKARA, 200, Cities.ESKISEHIR, 100, Cities.KONYA, 250));
        demiryolu.put(Cities.BILECIK, Map.of(Cities.ISTANBUL, 150, Cities.KOCAELI, 50, Cities.ANKARA, 150, Cities.ESKISEHIR, 50, Cities.KONYA, 200));
        demiryolu.put(Cities.ANKARA, Map.of(Cities.ISTANBUL, 250, Cities.KOCAELI, 200, Cities.BILECIK, 150));
        demiryolu.put(Cities.ESKISEHIR, Map.of(Cities.ISTANBUL, 200, Cities.KOCAELI, 100, Cities.BILECIK, 50, Cities.ANKARA, 100, Cities.KONYA, 150));
        demiryolu.put(Cities.KONYA, Map.of(Cities.ISTANBUL, 300, Cities.KOCAELI, 250, Cities.BILECIK, 200));

        // Havayolu ücretleri
        Map<Cities, Map<Cities, Integer>> havayolu = new HashMap<>();
        havayolu.put(Cities.ISTANBUL, Map.of(Cities.ANKARA, 1000, Cities.KONYA, 1200));
        havayolu.put(Cities.ANKARA, Map.of(Cities.ISTANBUL, 1000));
        havayolu.put(Cities.KONYA, Map.of(Cities.ISTANBUL, 1200));

        ulasimTablosu.put(TransportationType.KARAYOLU, karayolu);
        ulasimTablosu.put(TransportationType.DEMIRYOLU, demiryolu);
        ulasimTablosu.put(TransportationType.HAVAYOLU, havayolu);
    }

    public static int getUcret(TransportationType yolTipi, Cities kalkisSehir, Cities varisSehir) {
        try {
            return ulasimTablosu.get(yolTipi).get(kalkisSehir).get(varisSehir);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Bilgi bulunamadı. Lütfen geçerli bir ulaşım yolu, kalkış şehri ve varış şehri girin.");
            return -1;
        }
    }
}
