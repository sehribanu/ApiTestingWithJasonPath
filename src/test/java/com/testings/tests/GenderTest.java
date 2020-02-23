package com.testings.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GenderTest extends TestBase{
    /*
    1. Create a request by providing query parameter: gender, male or female
    2. Verify status code 200, content type application/json; charset=utf-8
    3. Verify that value of gender field is same from step 1
     */
    @Test
    public void genderTest() {
        Response response = given().accept(ContentType.JSON).
                queryParam("gender", "male").
                when().get("/");

        assertEquals(response.contentType(), "application/json; charset=utf-8");

        JsonPath json = response.jsonPath();
        String exp = "male";
        String act = json.getString("gender");
        assertEquals(exp,act);
    }

}
