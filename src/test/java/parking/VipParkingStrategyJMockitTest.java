package parking;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(JMockit.class)
public class VipParkingStrategyJMockitTest {

    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
        * by using JMockit to mock static method */

        new MockUp<ParkingLot>() {
            @Mock
            public int getBasicHourlyPrice() {
                return 25;
            }
        };

        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();

        assertEquals((Integer)50, vipParkingStrategy.calculateHourlyPrice());
    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using JMockit to mock static method */


    }
}
