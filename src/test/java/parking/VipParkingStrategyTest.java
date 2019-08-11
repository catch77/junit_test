package parking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

    @Mock
    CarDao carDao;
    @InjectMocks
    VipParkingStrategy injectdVipParkingStrategy = new VipParkingStrategy();

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    ParkingLot parkingLot = new ParkingLot("PARKING LOT", 0);
	    Car car = mock(Car.class);
        List<ParkingLot> parkingLots = new ArrayList<>();

        when(car.getName()).thenReturn("car");
        parkingLots.add(parkingLot);

	    VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());

	    doReturn(true).when(vipParkingStrategy).isAllowOverPark(any());

	    vipParkingStrategy.park(parkingLots, car);

        verify(vipParkingStrategy, times(0)).createNoSpaceReceipt(any());
        verify(vipParkingStrategy, times(1)).createReceipt(any(), any());
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        ParkingLot parkingLot = new ParkingLot("PARKING LOT", 0);
        Car car = mock(Car.class);
        List<ParkingLot> parkingLots = new ArrayList<>();

        when(car.getName()).thenReturn("ccc");
        parkingLots.add(parkingLot);

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());

        doReturn(false).when(vipParkingStrategy).isAllowOverPark(any());

        vipParkingStrategy.park(parkingLots, car);

        verify(vipParkingStrategy, times(1)).createNoSpaceReceipt(any());
        verify(vipParkingStrategy, times(0)).createReceipt(any(), any());

    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        when(carDao.isVip(any())).thenReturn(true);
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("AAA");
        Assert.assertEquals(true, injectdVipParkingStrategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */


    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
