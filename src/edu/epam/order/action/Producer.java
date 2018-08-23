package edu.epam.order.action;

import edu.epam.order.entity.*;
import edu.epam.order.exception.CustomException;
import edu.epam.order.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Producer implements Addition {
    private static Logger logger = LogManager.getLogger();

    private String name;
    private int number;
    private ArrayList<Order> orders;

    public String getName() {
        return name;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public Producer(String name) {
        this.name = name;
        this.number = IdGenerator.getProducerId();
        this.orders = new ArrayList<>();
    }

    public Order getOrder(int index) {
        try{
            return this.orders.get(index);
        }catch (ArrayIndexOutOfBoundsException aiooe){
            logger.error(aiooe.getMessage());
        }
        return null;
    }

    public void addOrder(Order readyOrder){
        this.orders.add(readyOrder);
    }
    public void createOrder(String movieName, int count) throws CustomException {
        LocalTime localTime = LocalTime.now();
        Order order = new Order(this.name,movieName,count,localTime);
        this.orders.add(order);
    }
    public void createOrder(String movieName, int count, WorkerType[] workers) throws CustomException{
        LocalTime localTime = LocalTime.now();
        Order order = new Order(this.name, movieName,count,workers,localTime);
        this.orders.add(order);
    }

    @Override
    public void addWorker(int orderNumber, String enumName) {
        try {
            if (this.getOrder(orderNumber).getMovie().addWorker(enumName)) {
                logger.log(Level.INFO, WorkerType.valueOf(enumName.toUpperCase()) + " was added");
            } else {
                logger.log(Level.INFO, "All complete or " + WorkerType.valueOf(enumName) + " is present");
            }
        }
        catch(IllegalArgumentException iae){
            logger.error(iae.getMessage());
        }
    }
}

