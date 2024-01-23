package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class ProductSteps {

    @Step("Creating new product")
    public ValidatableResponse creatingProduct(String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image){

        ProductPojo productPojo = new ProductPojo();

        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post(EndPoints.PRODUCT_POST)
                .then().log().all();
    }

    @Step("Getting the new product id ")
    public HashMap<String, Object> getProductInfoById(int Id) {
        String s1 = "findAll{it.Id == '";
        String s2 = "'}.get(0)";

        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then().statusCode(200)
                .extract()
                .path(s1 + Id + s2);
    }


    @Step("updating store detail ")
    public ValidatableResponse updatingProduct(int productId, String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image){

        ProductPojo productPojo = new ProductPojo();

        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .pathParam("id", productId)
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all();
    }


    @Step("Deleting product id")
    public ValidatableResponse deleteProduct(int Id){
        return SerenityRest.given().log().all()
                .pathParam("id", Id)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting product info")
    public ValidatableResponse getProduct(int Id){
        return SerenityRest.given().log().all()
                .pathParam("id", Id)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }

}
