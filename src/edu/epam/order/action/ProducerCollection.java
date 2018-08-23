package edu.epam.order.action;

import edu.epam.order.entity.Movie;
import edu.epam.order.entity.Order;

import java.util.ArrayList;

public class ProducerCollection {
    public static ArrayList<Producer> producers = new ArrayList<>();

    public static void AddOrders(ArrayList<Order> orders){
        for(Order order : orders){
            int counter = 0;
            if(producers.isEmpty()){
                producers.add(new Producer(order.getProducersName()));
                producers.get(producers.size() - 1).addOrder(order);
                continue;
            }
            while(!producers.get(counter).getName().equals(order.getProducersName())){
                if(++counter >= producers.size()){
                    break;
                }
            }
            if(counter < producers.size()){
                producers.get(counter).addOrder(order);
            }
            else{
                producers.add(new Producer(order.getProducersName()));
                producers.get(producers.size() - 1).addOrder(order);
            }
        }
    }
}
