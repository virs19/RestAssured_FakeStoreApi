package testcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.Payload;
import pojo.Carts;
import routes.Routes;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CartTests extends BaseClass {

    @Test
    public void testGetAllCartItems() {
        given()
                .when()
                .get(Routes.GET_ALL_CARTITEMS)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));

    }

    @Test
    public void testGetCartItemsById() {
        int cartId = configReader.getIntProperty("cartId");
        given()
                .pathParam("id", cartId)
                .when()
                .get(Routes.GET_CARTITEMS_BY_CART_ID)
                .then()
                .statusCode(200);
    }


    @Test
    public void testGetCartInDataRange() {
        String startDate = configReader.getProperty("startdate");
        String endDate = configReader.getProperty("enddate");
        Response response = given()
                .pathParams("startdate", startDate)
                .pathParams("enddate", endDate)
                .when()
                .get(Routes.GET_CART_DATE_RANGE)
                .then()
                .statusCode(200)
                .extract().response();
        List<String> cartdates = response.jsonPath().getList("date");
        assertThat(validateCartDatesWithinRange(cartdates, startDate, endDate), is(true));
    }

    @Test
    public void testGetCartItemsByUserId() {
        int userId = configReader.getIntProperty("userId");
        given()
                .pathParam("userId", userId)
                .when()
                .get(Routes.GET_CART_BY_USERID)
                .then()
                .statusCode(200)
                .body("userId", everyItem(equalTo(userId)));
    }

    @Test
    public void testGetCartItemsWithLimit() {
        given()
                .pathParam("limit", 3)
                .when()
                .get(Routes.GET_CART_WITH_LIMIT)
                .then()
                .statusCode(200)
                .body("size()", lessThanOrEqualTo(3));
    }

    @Test
    public void testGetSortedInDesc() {
        Response response = given()
                .pathParam("order", "desc")
                .when()
                .get(Routes.GET_CART_SORTED)
                .then()
                .statusCode(200)
                .extract().response();
        List<Integer> cartIds = response.jsonPath().getList("id", Integer.class);
        assertThat(isSortedDescending(cartIds), is(true));
    }

    @Test
    public void testSortedCartItemsInAsc() {
        Response response = given()
                .pathParam("order", "asc")
                .when()
                .get(Routes.GET_CART_SORTED)
                .then()
                .statusCode(200)
                .extract().response();
        List<Integer> cartIds = response.jsonPath().getList("id", Integer.class);
        assertThat(isSortedAscending(cartIds), is(true));
    }

    @Test
    public void testAddNewCart() {
        int userId = configReader.getIntProperty("userId");
        Carts newcart = Payload.cartsPayload(userId);
        given()
                .contentType(ContentType.JSON)
                .body(newcart)
                .when()
                .post(Routes.CREATE_CART)
                .then()
                .log().body()
                .statusCode(201)
                .body("id", notNullValue())
                .body("userId", notNullValue());

    }

    @Test
    public void updateCart() {
        int cartId = configReader.getIntProperty("cartId");
        int userId = configReader.getIntProperty("userId");
        Carts updatedCart = Payload.cartsPayload(userId);
        given()
                .pathParams("id", cartId)
                .contentType(ContentType.JSON)
                .body(updatedCart)
                .when()
                .put(Routes.UPDATE_CART)
                .then()
                .statusCode(200)
                .log().body()

                .body("id", notNullValue())
                .body("userId", notNullValue());
    }

    @Test
    public void testDeleteCart() {
        int cartId = configReader.getIntProperty("cartId");
        given()
                .pathParams("id", cartId)
                .when()
                .delete(Routes.DELETE_CART)
                .then()
                .statusCode(200);
    }

}
