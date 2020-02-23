package com.testings.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class AmountAndRegionsTest extends TestBase{
    /*
    1. Create request by providing query parameters: a valid region and amount (must be bigger than 1)
    2. Verify status code 200, content type application/json; charset=utf-8
    3. Verify that all objects have different name+surname combination
     */
    @Test
    public void amountAndRegions() {
        String validRegion = "france";
        int amount = 10;
        String expContentType = "application/json; charset=utf-8";

        Response response = given().
                queryParam("amount", amount).
                queryParam("region", validRegion).
                when().get("/");

        assertEquals(response.statusCode(), 200);
        System.out.println("response.statusCode() = " + response.statusCode());

        String actContentType = response.headers().getValue("Content-Type").toString();
        System.out.println("actContentType = " + actContentType);
        assertEquals(expContentType, actContentType);

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("name");
        List<String> surNames = json.getList("surname");

        List<String> fullName = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            fullName.add(names.get(i) + " " + surNames.get(i));
        }

        for (int i = 0; i < fullName.size(); i++) {
            for (int j = i+1; j < fullName.size(); j++) {
                System.out.println(fullName.get(i) + " " +(fullName.get(j)));
                assertFalse(fullName.get(i).equals(fullName.get(j)));

            }
        }
    }
}
