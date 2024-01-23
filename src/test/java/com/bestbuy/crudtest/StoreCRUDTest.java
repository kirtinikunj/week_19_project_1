package com.bestbuy.crudtest;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.steps.StoreSteps;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

@RunWith(SerenityRunner.class)
public class StoreCRUDTest {

    static String name = "StoreWatford";//for POST request

    static String type = "Games";//for POST request

    static String address = "1 High Street";
    static String address2 = "Watford";
    static String city = "Hertforshire";
    static String state = "England";
    static String zip = "WD1 1AA";
    static Double lat = 50.00;
    static Double lng = 50.00;
    static String hours = "Mon: 9 - 9";
    static HashMap<String, Object> services = new HashMap<String, Object>();

    static int Id;

    @Steps
    StoreSteps steps;

    @Title("This will crate new store")
    @Test
    public void test001() {

        ValidatableResponse response = steps.creatingStore(name, type, address, address2, city, state, zip, lat, lng, hours, services);
        response.statusCode(201);

        Id = response.extract().path("id");
        System.out.println("Store ID created" + Id);

    }
    @Title("Verify if the store was added to the application")
    @Test
    public void test002(){

        given()
                .pathParam("storeID", Id)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then().log().all()
                .statusCode(200);
    }

    @Title("Verify if the store was updated")
    @Test //updating record
    public void test003(){
        name = "storeLondon";
        ValidatableResponse  response = steps.updatingStore(Id, name, type, address, address2, city, state, zip, lat, lng, hours, services);
        response.statusCode(200);
    }

    @Title("Delete the store and verify if the store is deleted")
    @Test
    public void test004() {
        steps.deleteStore(Id).statusCode(200);
        steps.getStore(Id).statusCode(404);
    }



}
