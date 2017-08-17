package stqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 016 16.08.17.
 */
public class PointTests {

    @Test
    public void distanceWithSameXValueTest() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point( 3, 2);
        Assert.assertEquals(p1.distance(p2), 2.0);
    }

    @Test
    public void distanceWithSameYValueTest() {
        Point p1 = new Point(4, 2);
        Point p2 = new Point( 4, 10);
        Assert.assertEquals(p1.distance(p2), 8.0);
    }

    @Test
    public void distanceWithNegativesValuesTest() {
        Point p1 = new Point(-3, -2);
        Point p2 = new Point( -3, -5);
        Assert.assertEquals(p1.distance(p2), 3.0);
    }
}
