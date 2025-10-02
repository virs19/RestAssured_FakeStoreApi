package testcases;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import payloads.Payload;
import pojo.Product;
import routes.Routes;
import utils.DataProviders;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProductDataDrivenTests extends BaseClass {
    @Test(dataProvider ="jsonDataProvider",dataProviderClass = utils.DataProviders.class)

    public void testAddNewProduct(Map<String,String> data) {
        String title=data.get("title");
        double price=Double.parseDouble(data.get("price"));
        String description=data.get("description");
        String image=data.get("image");
        String category=data.get("category");

        Product newProduct=new Product(title,price,description,image,category);
        int productId=given()
                .contentType(ContentType.JSON)
                .body( newProduct)
        .when()
                .post(Routes.CREATE_PRODUCT)
        .then()
                .statusCode(201)
                .log().body()
                .body("id",notNullValue())
                .body("title",equalTo(newProduct.getTitle()))
                .extract().jsonPath().getInt("id");
        System.out.println("Product Id is: "+productId);

        //Delete Product
        given()
                .contentType(ContentType.JSON)
                .pathParams("id",productId)
        .when()
                .put(Routes.DELETE_PRODUCT)
        .then()
                .statusCode(200);
        System.out.println("Deleted Product Id is: "+productId);

    }

}
