package edu.epam.order.entity;

import edu.epam.order.catching.SimpleException;
import edu.epam.order.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;

public class Order {

    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_NAME_LENGTH = 20;
    public static final String DEFAULT_NAME = "Producer # ";
    public static final String REGEX_ENGLISH = "[A-z\\s]{3,20}";
    private int id;
    private String producersName;
    private Movie movie;
    private LocalTime localTime;
    private static Logger logger = LogManager.getLogger();

    public Order(String producersName, String movieName, int count, Worker[] workers, LocalTime localTime) {
        try {
            this.id = IdGenerator.getOrderId();
            this.localTime = localTime;
            this.producersName = producersName;
            if (assignName(movieName)) {
                this.movie = new Movie(movieName, count, producersName, this.id, workers);
            } else {
                this.movie = new Movie(DEFAULT_NAME + this.id, count, producersName, this.id, workers);
            }
        } catch (SimpleException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

    }

    public Order(String producersName, String movieName, int count, LocalTime localTime) {
        try {
            this.id = IdGenerator.getOrderId();
            this.localTime = localTime;
            this.producersName = producersName;
            if (assignName(movieName)) {
                this.movie = new Movie(movieName, count, producersName, this.id);
            } else {
                this.movie = new Movie(DEFAULT_NAME + this.id, count, producersName, this.id);
            }
        } catch (SimpleException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public int getId() {
        return id;
    }
    public LocalTime getLocalTime() {
        return localTime;
    }


    public static boolean assignName(String movieName) throws SimpleException {
        if (movieName == null) {
            throw new SimpleException("String is inValid");
        }
        return movieName.matches(REGEX_ENGLISH);
//        int counter = 0;
//        for (int i = 0; i < movieName.length(); i++) {
//            if ((movieName.charAt(i) < 91 && movieName.charAt(i) > 64) || (movieName.charAt(i) < 123 && movieName.charAt(i) > 96)) {
//                counter++;
//            }
//            else{
//                if(movieName.charAt(i) != ' ')
//                    return false;
//            }
//        }
//         return counter >= MIN_NAME_LENGTH && counter <= MAX_NAME_LENGTH;
    }

    public int orderSum(){
        int sum = 800;
        for (int i = 0; i < this.movie.getWorkers().length && this.movie.getWorkers()[i] != null; i++) {
            sum += this.movie.getWorkers()[i].getPrice();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("************************************************\n");
        sb.append("Заказ: " + this.id + "\n");
        sb.append("Продюсер: " + this.producersName + "\n");
        sb.append("Название: " + this.movie.getName() + "\n");
        sb.append("Дата создания: " + this.localTime.toString() + "\n");
        sb.append("------------------------------------------------" + "\n");
        String string1 = String.format("%10s%20d%s%n","Lease & tax",800," euro");
        sb.append(string1);
        for (int i = 0; i < this.movie.getWorkers().length; i++) {
            if (this.movie.getWorkers()[i] == null) {
                break;
            }
            string1 = String.format("%-10s%20d%s%n",this.movie.getWorkers()[i].getName(),this.movie.getWorkers()[i].getPrice()," euro");
            sb.append(string1);
        }
        sb.append("------------------------------------------------\n");
        sb.append("Всего:                              " + orderSum() + "\n");
        sb.append("Кол-во:                             " + this.movie.getCount() + "\n");
        sb.append("------------------------------------------------\n");
        sb.append("Общая сумма:                              " + orderSum() * this.movie.getCount() + "\n");

        return sb.toString();
    }
}
