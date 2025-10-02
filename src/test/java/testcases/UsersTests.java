package testcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.Payload;
import pojo.Users;
import routes.Routes;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersTests extends BaseClass {
    @Test
    public void testGetAllUsers() {
        given()
                .when()
                .get(Routes.GET_ALL_USERS)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testGetUserById() {
        int userID = configReader.getIntProperty("userId");
        given()
                .pathParam("id", userID)
                .when()
                .get(Routes.GET_USER_BY_ID)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void testGetUsersWithLimit() {
        given()
                .pathParam("limit", 3)
                .when()
                .get(Routes.GET_USERS_WITH_LIMIT)
                .then()
                .statusCode(200)
                .body("size()", equalTo(3));
    }

    @Test
    public void testGetUsersSortedInDesc() {
        Response response = given()
                .pathParam("order", "desc")
                .when()
                .get(Routes.GET_USERS_SORTED)
                .then()
                .statusCode(200)
                .extract().response();
        List<Integer> userIds = response.jsonPath().getList("id", Integer.class);
        assertThat(isSortedDescending(userIds), is(true));
    }

    @Test
    public void testGetUsersSortedInAsc() {
        Response response = given()
                .pathParam("order", "asc")
                .when()
                .get(Routes.GET_USERS_SORTED)
                .then()
                .statusCode(200)
                .extract().response();
        List<Integer> userIds = response.jsonPath().getList("id", Integer.class);
        assertThat(isSortedAscending(userIds), is(true));
    }

    @Test
    public void testAddNewUser() {
        Users users = Payload.usersPayload();
        int userId = given()
                .contentType(ContentType.JSON)
                .body(users)
                .when()
                .post(Routes.CREATE_USER)
                .then()
                .statusCode(201)
                .log().body()
                .body("id", notNullValue())
                //.body("email", equalTo(users.getEmail()))
                .extract().jsonPath().getInt("id");
        System.out.println("UserId is: " + userId);
    }

    @Test
    public void testUpdateUser() {
        int userId = configReader.getIntProperty("userId");
        Users updatedPayload = Payload.usersPayload();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(updatedPayload)
                .pathParams("id", userId)

                .when()
                .put(Routes.UPDATE_USER)
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        String firstName = response.jsonPath().getString("name.firstName");
        assertThat(firstName, is(updatedPayload.getName().getFirstName()));

    }

    @Test
    public void testDeleteUser() {
        int userId = configReader.getIntProperty("userId");
        given()
                .pathParams("id", userId)
                .when()
                .delete(Routes.DELETE_USER)
                .then()
                .statusCode(200);
    }
}
