package edu.epam.order.entity;

import edu.epam.order.exception.CustomException;
import edu.epam.order.util.IdGenerator;
import edu.epam.order.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;

public class Order {
    private static final String DEFAULT_NAME = "Producer # ";
    private static final int LEASE_TAX = 800;
    private static final int WORKER_MAX_COUNT = 7;
    private static Logger logger = LogManager.getLogger();

    private int orderId;
    private String producersName;
    private Movie movie;
    private LocalTime localTime;


    public Order(String producersName, String movieName, int count, WorkerType[] workers, LocalTime localTime) throws CustomException {
        this.orderId = IdGenerator.getOrderId();
        this.localTime = localTime;
        this.producersName = producersName;
        Validator validator = new Validator();
        if (validator.checkName(movieName)) {
            this.movie = new Movie(movieName, count, producersName, this.orderId, workers);
        } else {
            this.movie = new Movie(DEFAULT_NAME + this.orderId, count, producersName, this.orderId, workers);
        }
    }

    public Order(String producersName, String movieName, int count, LocalTime localTime) throws CustomException {
        this.orderId = IdGenerator.getOrderId();
        this.localTime = localTime;
        this.producersName = producersName;
        Validator validator = new Validator();
        if (validator.checkName(movieName)) {
            this.movie = new Movie(movieName, count, producersName, this.orderId);
        } else {
            this.movie = new Movie(DEFAULT_NAME + this.orderId, count, producersName, this.orderId);
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public String getProducersName() {
        return producersName;
    }


    public int orderSum() {
        int sum = LEASE_TAX;
        for (int i = 0; i < this.movie.getWorkers().length && this.movie.getWorkers()[i] != null; i++) {
            sum += this.movie.getWorkers()[i].getPrice();
        }
        return sum;
    }

    public String generateBill(){
        StringBuilder sb = new StringBuilder();
        sb.append("************************************************\n");
        sb.append("Заказ: " + this.orderId + "\n");
        sb.append("Продюсер: " + this.producersName + "\n");
        sb.append("Название: " + this.movie.getName() + "\n");
        sb.append("Дата создания: " + this.localTime.toString() + "\n");
        sb.append("------------------------------------------------" + "\n");
        String string1 = String.format("%-15s%25d%s%n", "Lease & tax", 800, " euro");
        sb.append(string1);
        int i = 0;
        while (i < WORKER_MAX_COUNT && this.movie.getWorkers()[i] != null) {
            string1 = String.format("%-15s%25d%s%n", this.movie.getWorkers()[i].getName(), this.movie.getWorkers()[i].getPrice(), " euro");
            sb.append(string1);
            i++;
        }
        sb.append("------------------------------------------------\n");
        sb.append("Всего:                              " + orderSum() + "\n");
        sb.append("Кол-во:                             " + this.movie.getCount() + "\n");
        sb.append("------------------------------------------------\n");
        sb.append("Общая сумма:                              " + orderSum() * this.movie.getCount() + "\n");
        return sb.toString();
    }
    @Override
    public String toString() {
       return this.generateBill();
    }
}
