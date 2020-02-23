package com.testings.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ThreeParamsTest extends TestBase{
    /*
    1. Create a request by providing query parameters: a valid region, gender and amount (must be bigger than 1)
    2. Verify status code 200, content type application/json; charset=utf-8
    3. Verify that all objects the response have the same region and gender passed in step 1
    */
    @Test
    public void threeParams(){
        String validRegion = "sweden";
        String validGender = "male";
        int amount = 12;

        Response response = given().
                queryParam("region", validRegion).
                queryParam("gender", validGender).
                queryParam("amount", amount).
                when().get("/");

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json; charset=utf-8");

        JsonPath json = response.jsonPath();
        List<String> regions = json.getList("region");
        List<String> genders = json.getList("gender");

        for (int i = 0; i < regions.size(); i++) {
            assertEquals(regions.get(i).toLowerCase(),validRegion);
            assertEquals(genders.get(i).toLowerCase(),validGender);
        }
    }
}
