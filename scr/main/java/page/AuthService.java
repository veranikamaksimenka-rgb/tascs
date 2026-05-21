package page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class AuthService {
    private Map<String, String> headers = new HashMap<>();
    private Object body;
    private int statusCode;

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public void doLogin() {
        Response response = given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://profile.onliner.by/sdapi/user.api/login")
                .then()
                .extract()
                .response();

        extractResponseData(response);
    }

    private void extractResponseData(Response response) {
        this.statusCode = response.getStatusCode();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
