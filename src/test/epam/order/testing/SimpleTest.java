package test.epam.order.testing;

import edu.epam.order.catching.SimpleException;
import edu.epam.order.entity.Movie;
import edu.epam.order.entity.Order;
import edu.epam.order.entity.Worker;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalTime;

public class SimpleTest {

    @Test
    public void nameTestNegative() throws SimpleException {
        boolean actual = Order.assignName("кракозябра");
        boolean expected = false;
        Assert.assertEquals(actual,expected);
    }



    @Test(dataProvider = "splash")
    public void nameTest(String input, boolean expected) throws SimpleException {

        boolean actual = Order.assignName(input);
        Assert.assertEquals(actual,expected);
    }

    @DataProvider(name = "splash")
    public Object[][] splash(){
        return new Object[][]{
                {"Gerrard",true},
                {"Jurgen Klopp", true},
                {"Cosplay", true},
                {"Жирорд и Пахом", false}
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
    public void addWorkerTestNegative(){
        Worker[] workers = {Worker.SCENARIO,Worker.ADVERTISMENT};
        Movie movie = new Movie("Forest Gump",1,"Zemekis",2,workers);
        boolean actual = movie.addWorker("SCENARIO");
        boolean expected = false;
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void sumTest(){
        Worker[] workers = {Worker.STAGE_DIRECTOR, Worker.MONTAGE, Worker.EQUIPMENT};
        Order order = new Order("Bay","Mile",4,workers,LocalTime.now());
        int actual = order.orderSum();
        int expected = 31800;
        Assert.assertEquals(actual,expected);
    }

}
