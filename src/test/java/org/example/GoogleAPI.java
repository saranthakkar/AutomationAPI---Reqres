package org.example;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GoogleAPI {
    @Test
    public void postDemo1(){
        given().baseUri("https://rahulshettyacademy.com").contentType(ContentType.JSON).body("{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n")
                .when().post("/maps/api/place/add/json?key=qaclick123")
                .then().log().all();
    }

    @Test
    public void deleteDemo1(){
        given().baseUri("https://rahulshettyacademy.com").contentType(ContentType.JSON).body("{\n" +
                "    \"place_id\":\"928b51f64aed18713b0d164d9be8d67f\"\n" +
                "}\n")
                .when().delete("/maps/api/place/delete/json?key=qaclick123")
                .then().log().all();

    }

    @Test
    public void testDemo1(){
        given().baseUri("https://rahulshettyacademy.com")
                .when().get("/maps/api/place/get/json?key=qaclick123&place_id=928b51f64aed18713b0d164d9be8d67f")  //404 not found
                .then().log().all()
                .assertThat().statusCode(404);
    }
}
