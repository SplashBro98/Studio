package edu.epam.order.entity;

import edu.epam.order.util.IdGenerator;

import java.util.Arrays;
import java.util.Random;


public class Movie {

    private String name;
    private int id;
    private int count;
    private int orderNumber;
    private String producersName;
    private Worker[] workers;

    public Movie(String name, int count, String producer, int orderNumber) {
        this.count = count;
        this.name = name;
        this.id = IdGenerator.createInt(89999) + 10000;
        this.producersName = producer;
        this.orderNumber = orderNumber;
        this.workers = new Worker[7];
    }
    public Movie(String name, int count, String producer, int orderNumber, Worker[] workers) {
        this.count = count;
        this.name = name;
        Random r = new Random();
        this.id = r.nextInt(89999) + 10000;
        this.producersName = producer;
        this.orderNumber = orderNumber;
        this.workers =  Arrays.copyOf(workers,7);

    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Worker[] getWorkers() {
        return workers;
    }
    public String getProducersName() {
        return producersName;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public boolean addWorker(String enumName){
        Worker worker = Worker.valueOf(enumName);
        boolean validWorker = false;
        for (int i = 0; i < 7; i++) {
            if(this.workers[i] == null){
                this.workers[i] = worker;
                validWorker = true;
                break;
            }
            else{
                if(this.workers[i].getName().equals(worker.getName())) {
                    validWorker = false;
                    break;
                }
            }
        }
        return validWorker;
    }





    @Override
    public String toString() {
        return "[" + this.orderNumber + ":" + this.producersName + ":" + this.name + ":" + this.count + "]";
    }
}
