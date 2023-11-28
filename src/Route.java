// Sefer bilgilerini içeren class. Kalkış noktası, varış noktası, ulaşım türü, gidilen mesafe ve güzergah
// gibi bilgileri içermelidir.

public class Route {

    private TransportationType yolTuru;
    private Cities departureCity;
    private Cities arrivalCity;
    private Cities[] stations;
    private int distance;

    public Route(TransportationType yolTuru, Cities departureCity, Cities arrivalCity, Cities[] stations, int distance) {
        this.yolTuru = yolTuru;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.stations = stations;
        this.distance = distance;
    }

        // SEFERLERİ OLUŞTURALIM.
        Route demiryolu1 = new Route(TransportationType.DEMIRYOLU, Cities.ISTANBUL, Cities.ANKARA,
        new Cities[]{Cities.KOCAELI, Cities.BILECIK, Cities.ESKISEHIR}, 750);
        Route demiryolu2 = new Route(TransportationType.DEMIRYOLU, Cities.ISTANBUL, Cities.KONYA,
        new Cities[]{Cities.KOCAELI, Cities.BILECIK, Cities.ESKISEHIR}, 900);
        Route karayolu1 = new Route(TransportationType.KARAYOLU, Cities.ISTANBUL, Cities.ANKARA,
        new Cities[]{Cities.KOCAELI}, 1500);
        Route karayolu2 = new Route(TransportationType.KARAYOLU, Cities.ISTANBUL, Cities.KONYA,
        new Cities[]{Cities.KOCAELI, Cities.ESKISEHIR}, 1200);
        Route havayolu1 = new Route(TransportationType.HAVAYOLU, Cities.ISTANBUL, Cities.KONYA,
        new Cities[]{}, 600);
        Route havayolu2 = new Route(TransportationType.HAVAYOLU, Cities.ISTANBUL, Cities.ANKARA,
        new Cities[]{}, 500);


//        Getter Methods
    public Cities getDepartureCity() {
        return departureCity;
    }

    public Cities getArrivalCity() {
        return arrivalCity;
    }

    public int getDistance() {
        return distance;
    }
}
