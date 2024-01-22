package service.test;
import ValidateResponse.CommanValidation;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import service.steps.GetAllBalance;
import com.github.dzieciou.testing.curl.*;

import java.util.HashMap;
import java.util.Map;

import static ValidateResponse.CommanValidation.validateResponse;



public class GetAllBalanceTest {
private GetAllBalance getAllBalance;
private CommanValidation commanValidation;
    @BeforeClass
    public void instanceSetup(){
        getAllBalance=new GetAllBalance();
        commanValidation = new CommanValidation();
            // Set up RestAssured with the curl-logger
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); // Log requests and responses if validation fails

        //RestAssured.filters(new CurlLoggingFilter(LogDetail.ALL));
           // RestAssured.baseURI = "https://api.example.com"; // Set your base URI

    }


    @Test(description = "get all balance")
    public void getAllBalance(){
        SoftAssert softAssert=new SoftAssert();
        Map<String,String> query=new HashMap<>();
        query.put("as_of_time","2022-03-20T00:00:00.000Z");
        query.put("currency_code","ALL");
        Response getAllBalalance=getAllBalance.getALLbalance(query);
        Assert.assertEquals(getAllBalalance.getStatusCode(), HttpStatus.SC_OK);
        Map<String,String> expected= new HashMap<>();
        expected.put("balances[0].currency","CHF");
        expected.put("balances[4].currency","EUR");
        validateResponse(getAllBalalance,expected,softAssert);
        softAssert.assertAll();

    }
}
