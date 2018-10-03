package edu.epam.order.reader;

import edu.epam.order.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.nio.cs.UnicodeEncoder;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReaderFromFile {
    private static final String PATH = "input\\input.txt";
    private static Logger logger = LogManager.getLogger();


    public List<String> readInfo(String filepath) throws IOException, CustomException {
        List<String> result;
        FileReader fileReader;
        File file = new File(filepath);
        if(file.exists()){
            fileReader = new FileReader(filepath);
        }
        else{
            fileReader = new FileReader(PATH);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Stream<String> stream = bufferedReader.lines();
        result = stream.collect(Collectors.toList());
        return result;
    }
}
