package test.epam.order.testing;

import edu.epam.order.action.Producer;
import edu.epam.order.action.ProducerCollection;
import edu.epam.order.creation.OrderCreator;
import edu.epam.order.entity.WorkerType;
import edu.epam.order.exception.CustomException;
import edu.epam.order.entity.Movie;
import edu.epam.order.entity.Order;
import edu.epam.order.reader.ReaderFromFile;
import edu.epam.order.validation.Validator;
import edu.epam.order.writer.WriterToFile;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SimpleTest {

    private static final String PATH = "input\\test.txt";
    Validator validator;
    ReaderFromFile reader;
    WriterToFile writer;
    OrderCreator orderCreator;

    @BeforeClass
    public void setUp(){
        validator = new Validator();
        reader = new ReaderFromFile();
        writer = new WriterToFile();
        orderCreator = new OrderCreator();
    }

    @Test
    public void nameTestNegative() throws CustomException {
        boolean actual = validator.checkName("кnothing");
        boolean expected = false;
        Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider = "data")
    public void nameTest(String input, boolean expected) throws CustomException {
        boolean actual = validator.checkName(input);
        Assert.assertEquals(actual,expected);
    }

    @DataProvider(name = "data")
    public Object[][] data(){
        return new Object[][]{
                {"Gerrard",true},
                {"Jurgen Klopp", true},
                {"Coldlay", true},
                {"Джеррард и Суарес", false},
                {"Us", false}
        };
    }

    @Test
    public void addWorkerTest(){
        Movie movie = new Movie("Infinity war",6,"Russo",4);
        boolean actual = movie.addWorker("ACTORS");
        boolean expected = true;
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void addWorkerAll(){
        WorkerType[] workers = {WorkerType.SCENARIO,WorkerType.ADVERTISMENT,WorkerType.STAGE_DIRECTOR,WorkerType.EQUIPMENT,WorkerType.MONTAGE,WorkerType.MUSICIAN,WorkerType.ACTORS};
        Movie movie = new Movie("Forest Gump",1,"Zemekis",2,workers);
        boolean actual = movie.addWorker("SCENARIO");
        boolean expected = false;
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void addWorkerTestNegative(){
        WorkerType[] workers = {WorkerType.SCENARIO,WorkerType.ADVERTISMENT};
        Movie movie = new Movie("Forest Gump",1,"Zemekis",2,workers);
        boolean actual = movie.addWorker("SCENARIO");
        boolean expected = false;
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void sumTest() throws CustomException{
        WorkerType[] workers = {WorkerType.STAGE_DIRECTOR, WorkerType.MONTAGE, WorkerType.EQUIPMENT};
        Order order = new Order("Bay","Mile",4,workers,LocalTime.now());
        int actual = order.orderSum();
        int expected = 31800;
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void readTest() throws IOException, CustomException {
        List<String> actual = reader.readInfo("input\\test.txt");
        List<String> expected = new ArrayList<>();
        expected.add("Cruz;Mission;6;Montage");
        expected.add("Richie;Big profit;1;Actors");
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void writeTest() throws IOException, CustomException{
        String testfile = "output\\testinfo.txt";
        writer.writeInfo(testfile,ProducerCollection.producers);
        List<String> actual = reader.readInfo(testfile);
        List<String> expected = new ArrayList<>();
        for (Producer pro : ProducerCollection.producers) {
            for (Order order : pro.getOrders()) {
                expected.add(order.toString() + "\n\n");
            }
            expected.add("\n");
        }
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void getCollectionTest() throws CustomException{
        Producer producer = new Producer("Richie");
        producer.createOrder("Cards and money",1);
        try {
            producer.getOrders().add(new Order("Fincher", "Fight club", 1, LocalTime.now()));
        }catch (UnsupportedOperationException ex){
            Assert.assertEquals(producer.getOrders().size(),1);
        }
    }
    @Test
    public void createOrderTest() throws CustomException{
        Order actual = new Order("Fincher","Fight club",1,LocalTime.now());
        Assert.assertEquals(actual.orderSum(),800);
        Assert.assertEquals(actual.getProducersName(),"Fincher");
        Assert.assertEquals(actual.getMovie().getCount(),1);
        Assert.assertEquals(actual.getMovie().getName(),"Fight club");

    }
    @Test
    public void orderIdTest() throws CustomException{
        Order actual = new Order("Fincher","Fight club",1,LocalTime.now());
        Assert.assertEquals(actual.getOrderId(), 10000);
    }


}
