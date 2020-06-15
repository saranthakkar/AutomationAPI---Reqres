package org.example;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresAPI {
    @Test
    public void getDemo(){
        given().baseUri("https://reqres.in/")
                .when().get("\n" +
                "/api/users?page=2")
                .then().log().all()                //to print result
               .assertThat().statusCode(200).and().body("data.id",hasItems(7,8,9,10,11,12)).and().contentType(ContentType.JSON);       //hasItems from hamcrest
    }

    @Test
    public void getDemo2(){
    given().baseUri("https://reqres.in/")
            .when().get("/api/users/2")
            .then().log().all()
            .assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("data.last_name",equalTo("Weaver"));

    }

    @Test
    public void postDemo(){
        given().baseUri("https://reqres.in/").contentType(ContentType.JSON).body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}")
                .when().post("/api/users")
                .then().log().all()
                //.assertThat().statusCode(201).and().body("name",is("morpheus"));
                .assertThat().statusCode(201).and().body("name",equalTo("morpheus"));


    }

    @Test
    public void postDemo2(){
        given().baseUri("https://reqres.in/").contentType(ContentType.JSON).body("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}")
                .when().post("/api/register")
                .then().log().all()
                .assertThat().statusCode(200).and().body("token",is("QpwL5tke4Pnpja7X4"));


    }
    @Test
    public void putDemo(){
        given().baseUri("https://reqres.in/").contentType(ContentType.JSON).body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}")
                .when().put("/api/users/2")
                .then().log().all()
                .assertThat().statusCode(200).and().body("name",is("morpheus"));
    }

    @Test
    public void patchDemo(){
        given().baseUri("https://reqres.in/").contentType(ContentType.JSON).body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}")
                .when().patch("/api/users/2")
                .then().log().all()
                .assertThat().statusCode(200).and().body("job",equalTo("zion resident"));

    }

}
