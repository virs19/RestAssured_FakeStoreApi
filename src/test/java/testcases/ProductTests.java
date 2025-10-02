package testcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.Payload;
import pojo.Product;
import routes.Routes;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProductTests extends BaseClass {

    @Test
    public void testGetAllProducts() {
        given()
                .when()
                .get(Routes.GET_ALL_PRODUCTS)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testProductById() {
        int productId = configReader.getIntProperty("productId");
        given()
                .pathParams("id", productId)
                .when()
                .get(Routes.GET_PRODUCT_BY_ID)
                .then()
                .statusCode(200);
    }

    @Test
    public void getProductWithLimit() {

        given()
                .pathParam("limit", 3)
                .when()
                .get(Routes.GET_PRODUCTS_WITH_LIMIT)
                .then()
                .statusCode(200)
                .body("size()", equalTo(3));
    }

    /**
     * Test to retrive order in descending
     */
    @Test
    public void testGetSortedProductInDesc() {
        Response response = given()
                .pathParam("order", "desc")
                .when()
                .get(Routes.GET_PRODUCTS_SORTED)
                .then()
                .statusCode(200)
                .extract().response();
        List<Integer> productIds = response.jsonPath().getList("id", Integer.class);
        assertThat(isSortedDescending(productIds), is(true));
    }

    /**
     * Test to retrive order in ascending
     */
    @Test
    public void testGetSortedProductInAsc() {
        Response response = given()
                .pathParam("order", "asc")
                .when()
                .get(Routes.GET_PRODUCTS_SORTED)
                .then()
                .statusCode(200)
                .extract().response();
        List<Integer> productIds = response.jsonPath().getList("id", Integer.class);
        assertThat(isSortedAscending(productIds), is(true));
    }

    @Test
    public void testGetAllCategories() {
        given()
                .when()
                .get(Routes.GET_ALL_CATEGORIES)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testGetCategoryById() {
        int productId = configReader.getIntProperty("productId");
        given()
                .pathParams("category", "electronics")
                .when()
                .get(Routes.GET_PRODUCTS_BY_CATEGORY)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("category", everyItem(notNullValue()))
                .body("category", everyItem(equalTo("electronics")));
    }

    @Test
    public void testCreateNewProduct() {
        Product newProduct = Payload.productPayload();
        int productId = given()
                .contentType(ContentType.JSON)
                .body(newProduct)
                .when()
                .post(Routes.CREATE_PRODUCT)
                .then()
                .statusCode(201)
                .log().body()
                .body("id", notNullValue())
                .body("title", equalTo(newProduct.getTitle()))
                .extract().jsonPath().getInt("id");
        System.out.println("Product Id is: " + productId);
    }

    @Test
    public void testUpdateProduct() {
        int productId = configReader.getIntProperty("productId");
        Product updatedPayload = Payload.productPayload();
        given()
                .contentType(ContentType.JSON)
                .body(updatedPayload)
                .pathParams("id", productId)

                .when()
                .put(Routes.UPDATE_PRODUCT)
                .then()
                .log().body()
                .statusCode(200)
                .body("title", equalTo(updatedPayload.getTitle()));
    }

    @Test
    public void testDeleteProduct() {
        int productId = configReader.getIntProperty("productId");
        given()
                .pathParams("id", productId)

                .when()
                .delete(Routes.DELETE_PRODUCT)
                .then()
                .statusCode(200);

    }
}

