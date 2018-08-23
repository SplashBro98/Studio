package edu.epam.order.writer;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import edu.epam.order.action.Producer;
import edu.epam.order.entity.Order;
import edu.epam.order.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriterToFile {
    public static final String PATH = "output/output.txt";
    private static Logger logger = LogManager.getLogger();

    public void writeInfo(String filepath, ArrayList<Producer> producers) throws IOException, CustomException {
        File file = new File(filepath);
        if(!file.exists()){
            throw new CustomException("This file doesn`t exist");
        }
        if(!file.canWrite()){
            logger.error("No access to file");
            return;
        }
        try(FileWriter fileWriter = new FileWriter(filepath)) {
            for (Producer pro : producers) {
                for (Order order : pro.getOrders()) {
                    fileWriter.write(order.toString() + "\n\n");
                }
                fileWriter.write("\n");
            }
        }
    }
}
