package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class StoreSteps {

    @Step("Creating new student")
    public ValidatableResponse creatingStore(String name, String type, String address, String address2, String city, String state, String zip, Double lat, Double lng, String hours, HashMap<String, Object> services){

        StorePojo storePojo = new StorePojo();

        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post(EndPoints.POST)
                .then().log().all();
    }

    @Step("Getting the new store id ")
    public HashMap<String, Object> getStoreInfoById(int Id) {
        String s1 = "findAll{it.Id == '";
        String s2 = "'}.get(0)";

        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then().statusCode(200)
                .extract()
                .path(s1 + Id + s2);
    }

    @Step("updating store detail ")
    public ValidatableResponse updatingStore(int productId, String name, String type, String address, String address2, String city, String state, String zip, Double lat, Double lng, String hours, HashMap<String, Object> services){

        StorePojo storePojo = new StorePojo();

        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);

        return SerenityRest.given().log().all()
                .pathParam("productID", productId)
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then().log().all();
    }

    @Step("Deleting store id")
    public ValidatableResponse deleteStore(int Id){
        return SerenityRest.given().log().all()
                .pathParam("storeID", Id)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }

    @Step("Getting store info")
    public ValidatableResponse getStore(int Id){
        return SerenityRest.given().log().all()
                .pathParam("storeID", Id)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }

}
