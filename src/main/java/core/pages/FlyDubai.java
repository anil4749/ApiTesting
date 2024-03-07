package core.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.Route;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FlyDubai {

	private Page page;

	private String sourceCity = "(//input[@id='airport-origin'])[1]";
	private String destinationCity = "(//input[@id='airport-destination'])[1]";
	private String date = "(//div[@class='date-beforeSelected'])[1]";
	private String passenger = "(//span[@class='bwPassenger'])[1]";
	private String search = "//button[@class='btn btn_yellow bookingBtnWith_icon m-btn-ar'][normalize-space()='Search']";

	private String total = "(//div[@id='fareLbl']/*)[4]";
	public String totalAmmount;
	

	// 2. page constructor:
	public FlyDubai(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public String getHomePageTitle() {
		String title =  page.title();
		System.out.println("page title: " + title);
		return title;
	}

	public String getHomePageURL() {
		String url =  page.url();
		System.out.println("page url : " + url);
		return url;
	}

	public FlyDubai doSearch() {
		page.click(search);
		return this;
	}

	public FlyDubai selectSource(String source) {
		page.fill(sourceCity, source);
		return this;
	}

	public FlyDubai selectDestination(String destination) {
		page.fill(destinationCity, destination);
		return this;
	}
	public FlyDubai selectDate(String date) {
		page.fill(date, date);
		return this;
	}
@Step("Search flignt")
	public void searchFlight(){
		try {
			// Click input[name="airport-destination"] >> nth=0
			page.locator("input[name=\"airport-destination\"]").first().click();
			// Click #mCSB_2_container >> text=Colombo Airport
			page.locator("#mCSB_2_container >> text=Colombo Airport").click();
			// Click text=27 >> nth=2
			page.locator("text=27").nth(2).click();
			// Click text=26 >> nth=1
			page.locator("text=26").nth(1).click();
			// Click text=Return One-way Multi-city / Stopovers + I have a promo code From Dubai All Airpo >> input[type="submit"]//
			page.locator("text=Return One-way Multi-city / Stopovers + I have a promo code From Dubai All Airpo >> input[type=\"submit\"]").click();
			Thread.sleep(2000);
		}catch (Exception ignored){
		}
	}
@Step("Select flight")
	public void selectFlights(){
		page.locator("text=18:55DubaiDXB04h 45minNon-stopFZ 569 (B737 MAX 8H)01:10ColomboCMB+1 day fromAED  >> img").click();
		// Click text=AED 495SELECT >> button
		page.locator("text=AED 495SELECT >> button").click();
		// Click text=Lowest fare fromAED 610
		page.locator("text=Lowest fare fromAED 610").click();
		// Click text=Lowest fareAED 645SELECT >> button
		page.locator("text=Lowest fareAED 645SELECT >> button").click();

	}
@Step("Select Luggage")
	public void selectLuggage(){
		// Click .baggageDiv >> nth=0
		page.locator(".baggageDiv").first().click();
		// Click fz-desktop-select-bag >> text=Continue to passenger details
		page.locator("fz-desktop-select-bag >> text=Continue to passenger details").click();
		totalAmmount=page.locator(total).textContent();
	}
@Step("Fill passanger details")
	public void fillPassangerDetails(){
		page.locator("#First_Name").fill("Tets");
		page.locator("#Last_Name").fill("Automation");
		// Click [id="Male\.Text"] >> text=Male
		page.locator("[id=\"Male\\.Text\"] >> text=Male").click();
		// Fill text=Email address Gender Male Female >> input[type="text"]
		page.locator("text=Email address Gender Male Female >> input[type=\"text\"]").fill("more.anil1693@gmail.com");
		// Click fz-country-smart-search-input-box div:has-text("Code") >> nth=4
		page.locator("fz-country-smart-search-input-box div:has-text(\"Code\")").nth(4).click();
		// Click text=United Arab Emirates
		page.locator("text=United Arab Emirates").click();
		// Click text=+971Code Mobile number >> input[type="text"]
		page.locator("text=+971Code Mobile number >> input[type=\"text\"]").click();
		// Fill text=+971Code Mobile number >> input[type="text"]
		page.locator("text=+971Code Mobile number >> input[type=\"text\"]").fill("553426114");

	}
	@Step("Review and verify booking")
	public void reviewBooking(){
		page.locator("button:has-text(\"Review booking\")").first().click();
		String bookingTotal = page.textContent("//div[@id='totalAmount']//label[@id='lblAmount']");
		System.out.println("ammount in booking page = "+totalAmmount +" : ammount in review page = "+bookingTotal);
		Assert.assertEquals(totalAmmount,bookingTotal,"verify total ammount");
	}

	public List<Response> getNetwokLog(String paathParames){
		List<Response> response = new ArrayList<>();
		page.onResponse(response1 -> {
			if (response1.url().contains(paathParames)){
				response.add(response1);
				System.out.println("URL: " + response1.url() + " , Response==>: " + new String(response1.body(), StandardCharsets.UTF_8));
			}
		});
	return response;
	}
}
