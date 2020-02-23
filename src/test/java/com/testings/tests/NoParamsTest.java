package com.testings.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NoParamsTest extends TestBase{
    /*
    1. Send a get request without providing any parameters
    2. Verify status code 200, content type application/json; charset=utf-8
    3. Verify that name, surname, gender, region fields have value
     */
    @Test
    public void noParamsTest() {
        Response response = given().accept(ContentType.JSON).
                when().get("/");

        System.out.println(response.contentType());
        assertEquals(response.contentType(), "application/json; charset=utf-8");

        JsonPath json = response.jsonPath();

        String name = json.getString("name");
        System.out.println("name = " + name);
        assertTrue(name.length() > 0);

        String surname = json.getString("surname");
        System.out.println("surname = " + surname);
        assertTrue(surname.length() > 0);

        String gender = json.getString("gender");
        System.out.println("gender = " + gender);
        assertTrue(gender.length() > 0);

        String region = json.getString("region");
        System.out.println("region = " + region);
        assertTrue(region.length() > 0);
    }
}
