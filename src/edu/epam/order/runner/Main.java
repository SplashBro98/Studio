package edu.epam.order.runner;

import edu.epam.order.action.ProducerCollection;
import edu.epam.order.creation.OrderCreator;
import edu.epam.order.entity.Order;

import edu.epam.order.exception.CustomException;
import edu.epam.order.reader.ReaderFromFile;
import edu.epam.order.writer.WriterToFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    static Logger logger = LogManager.getLogger();

    public static void main(String args[]) {
        try {
            //reading info from file
            ReaderFromFile reader = new ReaderFromFile();
            List<String> list = reader.readInfo("input\\input.txt");

            OrderCreator orderCreator = new OrderCreator();
            ArrayList<Order> orders = orderCreator.makeOrder(reader.readInfo("input\\input.txt"));
            ProducerCollection.AddOrders(orders);
            WriterToFile writer = new WriterToFile();
            writer.writeInfo("output\\output.txt",ProducerCollection.producers);

        } catch (CustomException ce){
            logger.error(ce.getMessage());
        } catch (IOException io){
            logger.error(io.getMessage());
        }
    }
}