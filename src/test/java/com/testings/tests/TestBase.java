package com.testings.tests;

import com.testings.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class TestBase {


    @BeforeTest
    public void setUpTest(){
        RestAssured.baseURI = ConfigurationReader.get("uinames.uri");

    }


}
