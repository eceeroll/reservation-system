// Sefer bilgilerini içeren class. Kalkış noktası, varış noktası, ulaşım türü, gidilen mesafe ve güzergah
// gibi bilgileri içermelidir.

import java.util.ArrayList;

public class Route {

    private TransportationType yolTuru;
    private Cities departureCity;
    private Cities arrivalCity;
    private Cities[] stations;
    private int distance;
    private static ArrayList<Route> allRoutes = new ArrayList<>();
    private String routeName;
    private int routeId;

    public Route(int routeId,String routeName, TransportationType yolTuru, Cities departureCity, Cities arrivalCity, Cities[] stations, int distance) {
        this.routeName = routeName;
        this.yolTuru = yolTuru;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.stations = stations;
        this.distance = distance;
        this.routeId = routeId;
    }

//    SEFERLERİN OLUŞTURULMASI VE ARRAYLIST OLUŞTURULMASI
    static {
        Route demiryolu1 = new Route(1,"Demiryolu Güzergah 1" ,TransportationType.DEMIRYOLU, Cities.ISTANBUL, Cities.ANKARA,
                new Cities[]{Cities.KOCAELI, Cities.BILECIK, Cities.ESKISEHIR}, 750);
        Route demiryolu2 = new Route(2,"Demiryolu Güzergah 2" ,TransportationType.DEMIRYOLU, Cities.ISTANBUL, Cities.KONYA,
                new Cities[]{Cities.KOCAELI, Cities.BILECIK, Cities.ESKISEHIR}, 900);
        Route karayolu1 = new Route(3,"Karayolu Güzergah 1",TransportationType.KARAYOLU, Cities.ISTANBUL, Cities.ANKARA,
                new Cities[]{Cities.KOCAELI}, 1500);
        Route karayolu2 = new Route(4,"Karayolu Güzergah 2", TransportationType.KARAYOLU, Cities.ISTANBUL, Cities.KONYA,
                new Cities[]{Cities.KOCAELI, Cities.ESKISEHIR}, 1200);
        Route havayolu1 = new Route(5,"Havayolu Güzergah 1",TransportationType.HAVAYOLU, Cities.ISTANBUL, Cities.KONYA,
                new Cities[]{}, 600);
        Route havayolu2 = new Route(6,"Havayolu Güzergah 2",TransportationType.HAVAYOLU, Cities.ISTANBUL, Cities.ANKARA,
                new Cities[]{}, 500);

        allRoutes.add(demiryolu1);
        allRoutes.add(demiryolu2);
        allRoutes.add(karayolu1);
        allRoutes.add(karayolu2);
        allRoutes.add(havayolu1);
        allRoutes.add(havayolu2);
    }

//        Getter Methods
    public int getRouteId() {
    return routeId;
}

    public String getRouteName(){
        return routeName;
    }

    public Cities getDepartureCity() {
        return departureCity;
    }

    public Cities getArrivalCity() {
        return arrivalCity;
    }

    public static ArrayList<Route> getAllRoutes(){
        return allRoutes;
    }
    public int getDistance() {
        return distance;
    }
}
