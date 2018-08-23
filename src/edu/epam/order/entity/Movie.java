package edu.epam.order.entity;

import edu.epam.order.util.IdGenerator;
import edu.epam.order.validation.Validator;

import java.util.Arrays;
import java.util.Random;


public class Movie {
    public static final int WORKER_MAX_COUNT = 7;
    public static final int MAX_ID_VALUE = 89999;
    public final static int ADDITION_FOR_ID = 10000;

    private int movieId;
    private String name;
    private int count;
    private int orderNumber;
    private String producersName;
    private WorkerType[] workers;
    private int workerCounter = 0;

    public Movie(String name, int count, String producer, int orderNumber) {
        this.count = count;
        this.name = name;
        this.movieId = IdGenerator.createInt(MAX_ID_VALUE) + ADDITION_FOR_ID;
        this.producersName = producer;
        this.orderNumber = orderNumber;
        this.workers = new WorkerType[WORKER_MAX_COUNT];
    }
    public Movie(String name, int count, String producer, int orderNumber, WorkerType[] workers) {
        this.count = count;
        this.name = name;
        this.movieId = IdGenerator.createInt(MAX_ID_VALUE) + ADDITION_FOR_ID;
        this.producersName = producer;
        this.orderNumber = orderNumber;
        this.workers =  Arrays.copyOf(workers,WORKER_MAX_COUNT);
        this.workerCounter = workers.length;
    }

    public int getMovieId() {
        return movieId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public WorkerType[] getWorkers() {
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
        WorkerType worker = WorkerType.valueOf(enumName.toUpperCase());
        Validator validator = new Validator();
        if(validator.checkWorker(worker,this.workers,this.workerCounter)){
            workers[this.workerCounter++] = worker;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.orderNumber + ":" + this.producersName + ":" + this.name + ":" + this.count + "]";
    }
}
