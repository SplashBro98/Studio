package edu.epam.order.validation;

import edu.epam.order.entity.WorkerType;
import edu.epam.order.exception.CustomException;

public class Validator {
    public static final String REGEX_ENGLISH = "[A-z\\s+]{3,20}";
    public static final int WORKER_MAX_COUNT = 7;

    public boolean checkName(String maybeName) throws CustomException{
        if (maybeName.isEmpty()) {
            throw new CustomException("String is inValid");
        }
        return maybeName.matches(REGEX_ENGLISH);
    }
    public boolean checkWorker(WorkerType worker, WorkerType[] array, int count){
        if(count == 7){
            return false;
        }
        boolean check = true;
        int i = 0;
        while(i < count  && array[i] != null){
            if(array[i].getName().equalsIgnoreCase(worker.getName())){
                check = false;
            }
            i++;
        }
        return check;
    }
}
