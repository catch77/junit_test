package sales;

import mockit.Mock;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class SalesAppTest {


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

	
}
