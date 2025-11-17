package com.api;

import com.google.gson.Gson;
import com.mongodb.util.JSON;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.apache.http.impl.client.BasicCookieStore;
import org.testng.annotations.Test;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
//导入RestAssured
import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.config.HttpClientConfig.httpClientConfig;

public class TestApi {

    @Test
    public void test1(){

//        get("http://httpbin.org/get").then().statusCode(200);

        given().queryParam("username","byhy")
                .queryParam("password","88888888")
                .when()
                .post("http://127.0.0.1/mgr/signin")
                .then()
                .log().body();
}

    @Test
    public void test2(){
        given().
                body("username=byhy&password=88888888").
                when().
                post("http://127.0.0.1/mgr/sign.html").
                then().
                log().all().statusCode(200);
    }

    @Test
    public void testWithProxy() {
        given()
                .proxy("127.0.0.1", 8888)
                .when()
                .get("http://mirrors.sohu.com/")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testPostXmlData() {
        String xmlPayload = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<WorkReport>\n" +
                "    <Overall>羊宫</Overall>\n" +
                "    <Progress>30%</Progress>\n" +
                "    <Problems>妃那</Problems>\n" +
                "</WorkReport>";

        given()
                .proxy("127.0.0.1", 8888)
                .header("Content-Type", "application/xml")
                .body(xmlPayload)
                .when()
                .post("http://httpbin.org/post")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPostJsonData() {
        // 创建JSON对象
        Map<String, Object> payload = new HashMap<>();
        payload.put("Overall", "良好");
        payload.put("Progress", "30%");

         //创建Problems数组
        List<Map<String, Object>> problems = new ArrayList<>();

        Map<String, Object> problem1 = new HashMap<>();
        problem1.put("No", 1);
        problem1.put("desc", "问题1....");
        problems.add(problem1);

        Map<String, Object> problem2 = new HashMap<>();
        problem2.put("No", 2);
        problem2.put("desc", "问题2....");
        problems.add(problem2);

        payload.put("Problems", problems);

        Gson gson = new Gson();
        String jsonPayload = gson.toJson(payload);

        given()
                //.proxy("127.0.0.1", 8888)
                .header("Content-Type", "application/json")
                .body(jsonPayload)
                .when()
                .post("http://httpbin.org/post")
                .then()
                .log().all()  // 添加日志输出以便调试
                .statusCode(200);
    }


    @Test
    public void testGetSohuMirrorWithProxy() {
        given()
                .proxy("127.0.0.1", 8888)
                .when()
                .get("http://mirrors.sohu.com/")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testPostFormDataSimple() {
        given()
                .formParam("1", 1)
                .formParam("2", 2)
                .when()
                .post("http://httpbin.org/post")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testSessionRequests() {
        String baseUrl = "http://127.0.0.1";

        // 第一个请求：登录，并保存cookies
        Response loginResponse = given()
                .baseUri(baseUrl)
                .formParam("username", "byhy")
                .formParam("password", "88888888")
                .when()
                .post("/api/mgr/signin")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        // 从登录响应中提取cookies
        Cookies cookies = loginResponse.detailedCookies();

        // 第二个请求：使用登录获得的cookies访问受保护资源
        given()
                .baseUri(baseUrl)
                .cookies(cookies)  // 显式传递cookies
                .queryParam("action", "list_customer")
                .queryParam("pagesize", 10)
                .queryParam("pagenum", 1)
                .queryParam("keywords", "")
                .when()
                .get("/api/mgr/customers")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void test(){
        //使用.proxy("127.0.0.1", 8888)代理，访问127.0.0.1/api/mgr/signin,需要携带username和password参数
        Response loginResponse =given()
                .proxy("127.0.0.1", 8888)
                .formParam("username", "byhy")
                .formParam("password", "88888888")
                .when()
                .post("http://127.0.0.1/api/mgr/signin")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        //获取cookie
        Cookies cookies = loginResponse.detailedCookies();

        given()
                .baseUri("http://127.0.0.1")
                .cookies(cookies)  // 显式传递cookies
                .queryParam("action", "list_customer")
                .queryParam("pagesize", 10)
                .queryParam("pagenum", 1)
                .queryParam("keywords", "")
                .when()
                .get("/api/mgr/customers")
                .then()
                .log().all()
                .statusCode(200);
    }


}
