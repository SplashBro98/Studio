package edu.epam.order.action;

import edu.epam.order.entity.*;
import edu.epam.order.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.ArrayList;

public class Producer implements Addition {
    private String name;
    private int number;
    private ArrayList<Order> orders;
    private static Logger logger = LogManager.getLogger();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public Producer(String name) {
        this.name = name;
        this.number = IdGenerator.getProducerId();
        this.orders = new ArrayList<>();
    }

    public Order getOrder(int a) {
        if(a < this.orders.size()) {
            return this.orders.get(a);
        }
        else {
            return null;
        }
    }
    public void createOrder(String movieName, int count){
        LocalTime localTime = LocalTime.now();
        Order order = new Order(this.name,movieName,count,localTime);
        this.orders.add(order);
    }
    public void createOrder(String movieName, int count, Worker[] workers){
        LocalTime localTime = LocalTime.now();
        Order order = new Order(this.name, movieName,count,workers,localTime);
        this.orders.add(order);
    }

    @Override
    public void addWorker(int orderNumber, String enumName) {
        if(!this.getOrder(orderNumber).getMovie().addWorker(enumName)) {
            logger.log(Level.INFO, "All complete or this object is present");
        }
        else {
            logger.log(Level.INFO,  Worker.valueOf(enumName) + " was added" );
        }
    }
}

