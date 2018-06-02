package com.maxsoft.restassured.demo;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
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

    // Endpoints
    private static final String oAuthBaseURI = "https://accounts.google.com/o/oauth2/token";
    private static final String fileUploadURI = "http://10.199.253.187:8085/api/cards/file";

    // Directories
    private static String currentDir = System.getProperty("user.dir");

    // DOCX file path and mime type
    private static final String docxFilePath = currentDir + File.separator + "resources" + File.separator + "DOCX file.docx";
    private static final String docxMimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

    // Authorization
    private static final String authorizationHeaderName = "X-Authorization";
    private static final String authorizationToken = "eyJraWQiOiJrMTA2OTQ4MTkwIiwiYWxnIjoiUlM1MTIifQ.eyJoY2MiOiJVUyIsInN1YiI6" +
            "ImZmZmZmZmZmNWEwNjBhMWFlNGIwYjhlNThlMjQ5NjRhIiwidHlwZSI6ImF0IiwiZXhwIjoxNTI3OTU1MTc1LCJpYXQiOjE1Mjc5NDQzNzQsInNlc3NpZC" +
            "I6ImMzNjllMjc4LTkyNTAtNDNjZC1hMmIzLTRhNGQ0NmY0ZDIwMiJ9.AYcCv3PmRhZoj865wv7GlvGuIkN8d07luwtM9M-qlmiIL9f1_n6Mc9WM4a5HH1N" +
            "7pgcXTUDcTKlPgSIIC0VdH5UOMNwUQtUYgq7xK5Wj9G8S1I9SaWo8vgLNdXxEDkrM2ahHs3qWBcVsZyGgNR8A3bJgmG3FNJuz0ypRpmAaWpk";

    private static Response response;
    private Map<String, String> formParams = new HashMap<>();

    @Step("Set form-data key value pairs <table>")
    public Map<String, String> createFormParamsMap(Table table) {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            formParams.put(row.getCell(columnNames.get(0)), row.getCell(columnNames.get(1)));
        }
        return formParams;
    }

    @Step("Invoke form-data API with plain text")
    public void sendFormDataRequest() {
        response = given()
                .config(
                        RestAssured.config()
                                .encoderConfig(
                                        encoderConfig()
                                                .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .formParams(formParams)
                .when()
                .post(oAuthBaseURI).then().extract().response();
        System.out.println("Form Params Hash Map: \n" + formParams);
        Gauge.writeMessage("Form Params Hash Map: \n" + formParams);
        System.out.println("Response is:\n" + response.prettyPrint());
        Gauge.writeMessage("Response is:\n" + response.prettyPrint());
        clearFormParamsHashMap();
    }

    @Step("Invoke form-data API with multipart file")
    public void sendFormDataRequestWithMultipartFile() {
        response = given()
                .config(
                        RestAssured.config()
                                .encoderConfig(
                                        encoderConfig()
                                                .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header(authorizationHeaderName, authorizationToken)
                .formParams(formParams)
                .multiPart("file", new File(docxFilePath), docxMimeType)
                .when()
                .post(fileUploadURI).then().extract().response();
        System.out.println("Form Params Hash Map: \n" + formParams);
        Gauge.writeMessage("Form Params Hash Map: \n" + formParams);
        System.out.println("Response is:\n" + response.prettyPrint());
        Gauge.writeMessage("Response is:\n" + response.prettyPrint());
        clearFormParamsHashMap();
    }

    public void clearFormParamsHashMap() {
        formParams.clear();
    }


}
