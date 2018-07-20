package edu.epam.order.entity;

public enum Worker {

    STAGE_DIRECTOR ("Stage Director", 12000),
    SCENARIO("Scenario", 950),
    MUSICIAN("Musician", 1300),
    ACTORS("Actors", 2000),
    ADVERTISMENT("Advertisment", 500),
    MONTAGE("Montage", 10000),
    EQUIPMENT("Equipment", 9000);

    private int price;
    private String name;

    Worker(String enumName, int price) {
        this.name = enumName;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
}


