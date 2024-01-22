package service.steps;

import base.TestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.RestClient;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class GetAllBalance extends TestBase {
    public Response getALLbalance(Map<String,String> queyparams){
      Response response=null;
        try {
            url=host+getAllBalance;
            Map<String, String> headers=new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", accessToken);
            RestClient restClient=new RestClient(url,null,headers,queyparams,null,null,null);
            response=restClient.getResponse();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

}
