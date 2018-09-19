package com.example.akash004.sdl_cargo_project;

public class init_Ship {

    private int id;
    private String Ship_name;
    private  String Ship_id;
    private String Date_of_Departure;
    private String Date_of_Arrival;
    private String Departure_Time;
    private String Arrival_Time;
    private String Price;
    private String Category;
    private String port[] = new String[10];


    public init_Ship(int id, String ship_name, String ship_id, String date_of_Departure, String date_of_Arrival, String departure_Time, String arrival_Time, String price, String category, String[] port) {
        this.id = id;
        Ship_name = ship_name;
        Ship_id = ship_id;
        Date_of_Departure = date_of_Departure;
        Date_of_Arrival = date_of_Arrival;
        Departure_Time = departure_Time;
        Arrival_Time = arrival_Time;
        Price = price;
        Category = category;
        this.port = port;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShip_name() {
        return Ship_name;
    }

    public void setShip_name(String ship_name) {
        Ship_name = ship_name;
    }

    public String getShip_id() {
        return Ship_id;
    }

    public void setShip_id(String ship_id) {
        Ship_id = ship_id;
    }

    public String getDate_of_Departure() {
        return Date_of_Departure;
    }

    public void setDate_of_Departure(String date_of_Departure) {
        Date_of_Departure = date_of_Departure;
    }

    public String getDate_of_Arrival() {
        return Date_of_Arrival;
    }

    public void setDate_of_Arrival(String date_of_Arrival) {
        Date_of_Arrival = date_of_Arrival;
    }

    public String getDeparture_Time() {
        return Departure_Time;
    }

    public void setDeparture_Time(String departure_Time) {
        Departure_Time = departure_Time;
    }

    public String getArrival_Time() {
        return Arrival_Time;
    }

    public void setArrival_Time(String arrival_Time) {
        Arrival_Time = arrival_Time;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPort(int p) {
        return port[p];
    }

    public void setPort(String[] port) {
        this.port = port;
    }

    public String[] getPorts() {
        return port;
    }
}
