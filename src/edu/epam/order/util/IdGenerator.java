package edu.epam.order.util;

import java.util.Random;

public class IdGenerator {
    private static Random random = new Random();
    private static int orderId = 10000;
    private static int producerId = 100;

    public static int createInt(int bound){
        return random.nextInt(bound);
    }

    public static int getOrderId() {
        return orderId++;
    }
    public static int getProducerId() {
        return producerId++;
    }
}
