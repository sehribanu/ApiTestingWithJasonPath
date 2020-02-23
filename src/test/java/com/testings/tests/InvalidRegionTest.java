package com.testings.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class InvalidRegionTest extends TestBase {
    /*
    1. Create a request by providing query parameter: invalid region
    2. Verify status code 400 and status line contains Bad Request
    3. Verify that value of error field is Region or language not found
     */
    @Test
    public void invalidRegion() {
        String invRegion = "maramala";
        String expError = "Region or language not found";

        Response response = given().accept(ContentType.JSON).
                queryParam("region", invRegion).
                when().get("/");

        assertEquals(response.statusCode(), 400);
        System.out.println("response = " + response.statusCode());

        JsonPath json = response.jsonPath();
        String actError = json.getString("error");
        System.out.println("actError = " + actError);

        assertEquals(expError, actError);
    }
}
