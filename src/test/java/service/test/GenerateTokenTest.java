package service.test;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import dot.GenerateTokenDto;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import lombok.val;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import service.steps.GenerateToken;

import static ValidateResponse.CommanValidation.getValueAsString;
import static io.restassured.RestAssured.given;

public class GenerateTokenTest {
private GenerateToken generateToken;
    @BeforeClass
    public void instanceSetup(){
        generateToken=new GenerateToken();
    }


    @Test(description = "Generate Access Token")
    public void genrateAccessToken(){
        SoftAssert softAssert=new SoftAssert();
        GenerateTokenDto generateTokenDto= GenerateTokenDto.builder().customer_id("170392802").build();
        final Response getAllBalalance = generateToken.generateToken(generateTokenDto);
        Assert.assertEquals(getAllBalalance.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertNotNull(getValueAsString(getAllBalalance,"client_token"));
        softAssert.assertAll();

    }






}


