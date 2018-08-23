package edu.epam.order.creation;

import edu.epam.order.entity.Order;
import edu.epam.order.entity.WorkerType;
import edu.epam.order.exception.CustomException;
import edu.epam.order.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OrderCreator {

    public static final int SIZE_WITH_WORKERS_ARRAY = 4;
    public static final int SIZE_WITHOUT_ARRAY = 3;
    public static final int PRO_NAME_POSITION = 0;
    public static final int MOVIE_NAME_POSITION = 1;
    public static final int COUNT_POSITION = 2;
    public static final int WORKERS_POSITION = 3;
    public static final String DIGIT_REGEX = "\\d";
    private static Logger logger = LogManager.getLogger();


    public Order makeOrder(String input) throws CustomException {
        Order result;
        String[] toTransfrom = input.trim().split(";");
        if (toTransfrom.length != SIZE_WITHOUT_ARRAY && toTransfrom.length != SIZE_WITH_WORKERS_ARRAY) {
            throw new CustomException("Incorrect input string");
        }
        String proName = toTransfrom[PRO_NAME_POSITION];
        String movName = toTransfrom[MOVIE_NAME_POSITION];
        int count = 0;
        if (toTransfrom[COUNT_POSITION].matches(DIGIT_REGEX)) {
            count = Integer.parseInt(toTransfrom[COUNT_POSITION]);
        } else {
            throw new CustomException("Incorrect count string");
        }
        if (toTransfrom.length == SIZE_WITH_WORKERS_ARRAY) {
            String[] workers = toTransfrom[WORKERS_POSITION].split(",");
            int counter = 0;
            WorkerType[] enumWorkers = new WorkerType[counter];
            Validator validator = new Validator();
            for (String string : workers) {
                try {
                    WorkerType worker = WorkerType.valueOf(string.toUpperCase());
                    if (validator.checkWorker(worker, enumWorkers, counter)) {
                        enumWorkers = Arrays.copyOf(enumWorkers, ++counter);
                        enumWorkers[counter - 1] = worker;
                    }
                } catch (IllegalArgumentException enu) {
                    logger.error(enu.getMessage());
                }
            }
            result = new Order(proName, movName, count, enumWorkers, LocalTime.now());
        } else {
            result = new Order(proName, movName, count, new WorkerType[0], LocalTime.now());
        }
        return result;
    }

    public ArrayList<Order> makeOrder(List<String> input) {
        ArrayList<Order> result = new ArrayList<>();
        for (String inString : input) {
            try {
                result.add(this.makeOrder(inString));
            }
            catch(CustomException ce){
                logger.error(ce.getMessage());
            }
        }
        return result;
    }
}
