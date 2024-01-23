package core;//

import com.github.dzieciou.testing.curl.CurlHandler;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.slf4j.event.Level;

public class RestClient {
    private String url;
    private String payload;
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private Map<String, String> formParams;
    private String contentType;
    private ContentType encoder;
    private RestAssuredConfig config;
    private final List<String> curls = new ArrayList();

    public RestClient() {
    }

    public RestClient(String url, String payload, Map<String, String> headers, Map<String, String> queryParams, Map<String, String> formParams, String contentType, ContentType encoder) {
        this.url = url;
        this.payload = payload;
        this.headers = headers;
        this.queryParams = queryParams;
        this.formParams = formParams;
        this.contentType = contentType;
        this.encoder = encoder;

    }

    public Response getResponse() {
        return this.httpRequests("GET");
    }

    public Response postResponse() {
        return this.httpRequests("POST");
    }

    public Response putResponse() {
        return this.httpRequests("PUT");
    }

    public Response deleteResponse() {
        return this.httpRequests("DELETE");
    }

    public Response patchResponse() {
        return this.httpRequests("PATCH");
    }

    private Response httpRequests(String request) {
        RequestSpecification requestSpecification = this.getRequestSpecification(RestAssured.given()).when();
        Response res;
        switch (request.toUpperCase()) {
            case "GET":
                res = requestSpecification.get(this.url);
                break;
            case "POST":
                res = requestSpecification.post(this.url);
                break;
            case "PUT":
                res = requestSpecification.put(this.url);
                break;
            case "DELETE":
                res = requestSpecification.delete(this.url);
                break;
            case "PATCH":
                res = requestSpecification.patch(this.url);
                break;
            default:
                throw new IllegalArgumentException("Invalid http request");
        }

        Response response = (res.then()).extract().response();
        curlReporting(String.valueOf(response.getStatusCode()),response);
        System.out.println("Curl --> " + this.curls.get(0));
        System.out.println("Response --> " +response.body().asString());
        return response;
    }

    private RequestSpecification getRequestSpecification(RequestSpecification reqSpec) {
        if (this.headers != null) {
            reqSpec.headers(this.headers);
        }

        if (this.queryParams != null) {
            reqSpec.queryParams(this.queryParams);
        }

        if (this.formParams != null) {
            reqSpec.formParams(this.formParams);
        }

        if (this.payload != null) {
            reqSpec.body(this.payload);
        }
        CurlHandler handler = (curl, options) -> {
            Options.builder().useLogLevel(Level.INFO).build();
            this.curls.add(curl);
        };
        this.config = CurlRestAssuredConfigFactory.createConfig(Collections.singletonList(handler));
        if (this.encoder != null && this.contentType != null) {
            reqSpec.config(this.config.encoderConfig((new EncoderConfig()).encodeContentTypeAs(this.contentType, this.encoder)));
        } else {
            reqSpec.config(this.config);
        }

        return reqSpec;
    }

    private void curlReporting(String statusCode, Response response) {
        Allure.addAttachment("curl logger",curls.get(0));
        Allure.addAttachment("response body", response.body().print());
        Allure.addAttachment("response headers", response.headers().toString());
    }
}
