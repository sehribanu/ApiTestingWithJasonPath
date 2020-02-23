package com.testings.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InvalidGenderTest extends TestBase{
    /*
    1. Create a request by providing query parameter: invalid gender
    2. Verify status code 400 and status line contains Bad Request
    3. Verify that value of error field is Invalid gender
     */
    @Test
    public void invalidGender() {
        String invalidGender = "mefale";
        String statsLine = "Bad Request";
        String expErrorField = "Invalid gender";

        Response response = given().accept(ContentType.JSON).
                queryParam("gender", invalidGender).
                when().get("/");

        assertEquals(response.statusCode(), 400);
        System.out.println("response.statusCode() = " + response.statusCode());

        assertTrue(response.statusLine().contains(statsLine));
        System.out.println("response.statusLine() = " + response.statusLine());

        JsonPath jsonPath = response.jsonPath();
        String actErrorField = jsonPath.getString("error");
        System.out.println("actErrorField = " + actErrorField);
        assertEquals(expErrorField, actErrorField);
    }
}
