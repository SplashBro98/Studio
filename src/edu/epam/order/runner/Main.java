package edu.epam.order.runner;

import edu.epam.order.action.Producer;
import edu.epam.order.entity.Order;

import edu.epam.order.entity.Worker;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        Producer producer = new Producer("Spielberg");
        producer.createOrder("Pulp Fiction", 2);
        logger.log(Level.INFO,producer.getOrder(0).getMovie());

        Producer producer1 = new Producer("Peele");
        producer1.createOrder("Us",4);
        logger.log(Level.INFO,producer1.getOrder(0).getMovie());

        producer.addWorker(0,"SCENARIO");
        Worker[] workers = new Worker[3];
        workers[0] = Worker.ADVERTISMENT;
        workers[1] = Worker.ACTORS;
        workers[2] = Worker.MONTAGE;
        producer.createOrder("Jaws",3,workers);

//        producer.addWorker(0,"STAGE_DIRECTOR");
//        producer.addWorker(0,"MONTAGE");
//        producer.addWorker(0,"ACTORS");
//        producer.addWorker(0,"EQUIPMENT");
//        producer.addWorker(0,"MONTAGE");
//        producer.addWorker(0,"ADVERTISMENT");
//        producer.addWorker(0,"MUSICIAN");
//        producer.addWorker(0,"MUSICIAN");
       // logger.log(Level.INFO, producer.getOrder(0).toString());
       // logger.log(Level.INFO, producer.getOrder(1).toString());


    }

}
