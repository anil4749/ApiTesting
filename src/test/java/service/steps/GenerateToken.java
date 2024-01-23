package service.steps;

import base.TestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.RestClient;
import dot.GenerateTokenDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.*;

public class GenerateToken extends TestBase {
    ObjectMapper objectMapper=new ObjectMapper();
    private Map<String, String> headers=new HashMap<>();
    private Map<String, String> queryParams=new HashMap<>();
    private String payload=null;
    String url;

 @Step("get token")
    public Response generateToken(GenerateTokenDto requestBodyDto){
     Response response=null;
      try {
          url=host+generateToken;
          Map<String, String> headers=new HashMap<>();
          headers.put("Content-Type", "application/json");
          headers.put("Authorization", accessToken);
          String payload=objectMapper.writeValueAsString(requestBodyDto);
          RestClient restClient=new RestClient(url,payload,headers,queryParams,null,null,null);
          response=restClient.postResponse();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Step("step 1")
        public void step1(){

        }

}
