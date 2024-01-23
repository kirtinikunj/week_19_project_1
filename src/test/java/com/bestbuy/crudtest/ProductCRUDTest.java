package com.bestbuy.crudtest;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.steps.ProductSteps;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;

@RunWith(SerenityRunner.class)
public class ProductCRUDTest extends TestBase {

    static String name = "Lego";
    static String type = "Technic";
    static int price = 25;
    static int shipping = 5;
    static String upc = "Not Applicable";
    static String description = "Lego Technic car amazing fun for hours";
    static String manufacturer = "Lego";
    static String model = "Lego112";
    static String url = "url";
    static String image = "image1";

    static int productId;


    @Steps
    ProductSteps steps;

    @Title("This will crate new product")
    @Test
    public void test001() {

        ValidatableResponse response = steps.creatingProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.statusCode(201);

        productId = response.extract().path("id");
        System.out.println("Product ID created" + productId);

    }

    @Title("Verify if the product was added to the application")
    @Test
    public void test002(){

        given()
                .pathParam("id", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().log().all()
                .statusCode(200);
    }

    @Title("Verify if the product was updated")
    @Test //updating record
    public void test003(){
        name = "LegoMachine";
        ValidatableResponse  response = steps.updatingProduct(productId, name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.statusCode(200);
    }

    @Title("Delete the product and verify if the store is deleted")
   @Test
    public void test004() {
        steps.deleteProduct(productId).statusCode(200);
        steps.getProduct(productId).statusCode(404);
    }

}
