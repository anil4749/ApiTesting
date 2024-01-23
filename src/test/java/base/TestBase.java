package base;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import io.restassured.config.RestAssuredConfig;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TestBase {
    public  String accessToken="Bearer A21AAJ_FkDfuEBqGWk_jq9JolAZRKnrewyAk1M62vV8ZSGNlAZZsPMlu8qq9Wmmj8vEb3UJdqMkRpGAfBSfROpIT0gf4eIQFA";
    public  String host="https://api-m.sandbox.paypal.com";
    public  String getAllBalance="/v1/reporting/balances";
    public String generateToken="/v1/identity/generate-token";
    public  String url= null;



}
