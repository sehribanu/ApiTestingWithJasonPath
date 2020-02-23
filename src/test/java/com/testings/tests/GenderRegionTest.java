package com.testings.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GenderRegionTest extends TestBase{
    /*
    1. Create a request by providing query parameters: a valid region and gender NOTE: Available region values are given in the documentation
    2. Verify status code 200, content type application/json; charset=utf-8
    3. Verify that value of gender field is same from step 1
    4. Verify that value of region field is same from step 1
     */
    @Test
    public void genderRegionTest() {
        String expectedGender = "female";
        String expectedRegion = "germany";

        Response response = given().accept(ContentType.JSON).
                queryParam("gender", expectedGender).
                queryParam("region", expectedRegion).
                when().get("/");

        assertEquals(response.statusCode(), 200);

        System.out.println("response.statusCode() = " + response.statusCode());
        JsonPath json = response.jsonPath();
        String actualGender = json.getString("gender");
        System.out.println("actualGender = " + actualGender.toLowerCase());
        String actualRegion = json.getString("region");
        System.out.println("actualRegion = " + actualRegion.toLowerCase());

        assertTrue(expectedGender.equals(actualGender.toLowerCase()));
        assertTrue(expectedRegion.equals(actualRegion.toLowerCase()));
    }
}
