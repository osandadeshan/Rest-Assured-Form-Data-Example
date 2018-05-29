package com.maxsoft.restassured.demo;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

/**
 * Project Name : Rest Assured Form-Data Example
 * Developer    : Osanda Deshan
 * Date         : 5/29/2018
 * Time         : 8:21 PM
 * Description  :
 **/


public class FormDataExample {

    static String baseURI = "https://accounts.google.com/o/oauth2/token";
    public static Response response;
    Map<String, String> formParams = new HashMap<>();

    @Step("Set form-data key value pairs <table>")
    public Map<String, String> createFormParamsMap(Table table) {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            formParams.put(row.getCell(columnNames.get(0)), row.getCell(columnNames.get(1)));
        }
        return formParams;
    }

    @Step("Invoke API")
    public void sendFormDataRequest() {
        response = given()
                .config(
                        RestAssured.config()
                                .encoderConfig(
                                        encoderConfig()
                                                .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .formParams(formParams)
                .when()
                .post(baseURI).then().extract().response();
        System.out.println("Form Params Hash Map: \n" + formParams);
        Gauge.writeMessage("Form Params Hash Map: \n" + formParams);
        System.out.println("Response is:\n" + response.asString());
        Gauge.writeMessage("Response is:\n" + response.asString());
        clearFormParamsHashMap();
    }

    public void clearFormParamsHashMap() {
        formParams.clear();
        System.out.println("After clearing the Hash Map: \n" + formParams);
        Gauge.writeMessage("After clearing the Hash Map: \n" + formParams);
    }


}
