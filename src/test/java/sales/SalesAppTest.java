package sales;

import com.sun.org.apache.bcel.internal.generic.FADD;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SalesAppTest {

	@InjectMocks
	SalesApp salesApp;
	@Mock
	SalesDao salesDao;
	@Mock
	SalesReportDao salesReportDao;
	@Mock
	EcmService ecmService;

	@Test
	public void testGenerateReport() {
		SalesApp salesApp = mock(SalesApp.class);
		salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);
	}

	@Test
	public void testGetHeader_givenIsNatTradeFalse_thenReturnHeaderContainLocalTime() {
		SalesApp salesApp = new SalesApp();
		List<String> headers = salesApp.getHeaders(false);
		Assert.assertEquals("Local Time", headers.get(3));
	}

	@Test
	public void testGetSales_givenSalesId_thenReturnSales() {
		Sales sales = mock(Sales.class);
		when(salesDao.getSalesBySalesId(anyString())).thenReturn(sales);
		Sales sales1 = salesDao.getSalesBySalesId("DUMMY");
		Assert.assertEquals(sales, sales1);
	}

	@Test
	public void testIsSalesOutOfDate_givenStartDateIsThreeDaysAgoAndEndDateIsYesterday_thenReturnTrue() {
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.DATE, -3);

		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DATE, -1);

		Sales sales = mock(Sales.class);
		when(sales.getEffectiveFrom()).thenReturn(startDate.getTime());
		when(sales.getEffectiveTo()).thenReturn(endDate.getTime());
		Assert.assertEquals(true, salesApp.isSalesOutOfDate(sales));
	}

}
