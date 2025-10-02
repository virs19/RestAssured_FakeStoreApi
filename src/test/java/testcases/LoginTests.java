package testcases;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import payloads.Payload;
import pojo.Login;
import routes.Routes;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTests extends BaseClass {

    @Test
    public void testInvalidLogin() {
        Login login = Payload.loginPayload();
        given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post(Routes.CREATE_AUTH_TOKEN)
                .then()
                .statusCode(401)
                .log().body()
                .body(equalTo("username or password is incorrect"));
    }

    @Test
    public void testValidLogin() {
        String username = configReader.getProperty("username");
        String password = configReader.getProperty("password");
        Login login = new Login(username, password);
        token = given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post(Routes.CREATE_AUTH_TOKEN)
                .then()
                .statusCode(201)
                .log().body()
                .body("token", notNullValue())
                .extract().jsonPath().getString("token");
        System.out.println("Token is::" + token);

    }
}
