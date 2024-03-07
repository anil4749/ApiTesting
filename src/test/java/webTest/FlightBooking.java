package webTest;
import com.microsoft.playwright.*;
import base.UIBaseTest;
import core.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

import static javax.swing.UIManager.put;

public class FlightBooking extends UIBaseTest {

	@Test
	public void flightBookingReviewTest() {
		String actualTitle = flyDubai.getHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
		flyDubai.searchFlight();
		flyDubai.getNetwokLog("/api/flights/");
		flyDubai.selectFlights();
		flyDubai.selectLuggage();
		flyDubai.fillPassangerDetails();
		flyDubai.reviewBooking();

	}
}
