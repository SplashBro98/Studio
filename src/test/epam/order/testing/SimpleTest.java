package test.epam.order.testing;

import edu.epam.order.catching.SimpleException;
import edu.epam.order.entity.Movie;
import edu.epam.order.entity.Order;
import edu.epam.order.entity.Worker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest {

    @Test
    public void nameTest() throws SimpleException {
        boolean actual = Order.assignName("кракозябра");
        boolean expected = false;
        Assert.assertEquals(actual,expected);
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

}
