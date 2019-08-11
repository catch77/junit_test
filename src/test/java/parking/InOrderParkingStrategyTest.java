package parking;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    ParkingLot parkingLot = mock(ParkingLot.class);
	    Car car = mock(Car.class);
        List<ParkingLot> parkingLots = new ArrayList<>();

        when(parkingLot.getName()).thenReturn("parking lot");
        when(car.getName()).thenReturn("car");
        parkingLots.add(parkingLot);

	    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
	    Receipt receipt = inOrderParkingStrategy.park(parkingLots, car);

        Assert.assertEquals("car", receipt.getCarName());
        Assert.assertEquals("parking lot", receipt.getParkingLotName());

    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */

    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    InOrderParkingStrategy inOrderParkingStrategy = Mockito.spy(new InOrderParkingStrategy());

	    ParkingLot parkingLot = new ParkingLot("parking lot", 0);
        Car car = mock(Car.class);

        List<ParkingLot> parkingLots = new ArrayList<>();

        when(car.getName()).thenReturn("car");
        parkingLots.add(parkingLot);

        inOrderParkingStrategy.park(parkingLots, car);

        verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(any());
        verify(inOrderParkingStrategy, times(0)).createReceipt(any(), any());
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
