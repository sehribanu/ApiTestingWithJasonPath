package com.testings.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class AmountCountTests extends TestBase{
    /*
    1. Create a request by providing query parameter: amount (must be bigger than 1)
    2. Verify status code 200, content type application/json; charset=utf-8
    3. Verify that number of objects returned in the response is same as the amount passed in step 1
     */
    @Test
    public void amountCount(){
        int expAmount = 18;
        Response response = given().
                queryParam("amount", expAmount).
                when().get("/");

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json; charset=utf-8");

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("name");

        assertEquals(names.size(),expAmount);

    }
}
