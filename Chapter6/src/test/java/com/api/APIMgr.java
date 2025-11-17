package com.api;// 确保文件位于正确的目录结构中: src/test/java/com/api/APIMgr.java

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Test // 类级别的@Test注解，使所有public方法都成为测试方法
public class APIMgr {

    private Cookies cookies;
    Response loginResponse;

    /**
     * 管理员登录
     */
    public void mgrLogin(String username, String password) {
        loginResponse = given()
                //.proxy("127.0.0.1", 8888)
                .formParam("username", username)
                .formParam("password", password)
                .when()
                .post("http://127.0.0.1/api/mgr/signin")
                .then()
                .statusCode(200)
                .extract().response();
        cookies = loginResponse.detailedCookies();
        System.out.println(loginResponse.statusLine());
        System.out.println(loginResponse.header("Content-Type"));
        System.out.println(loginResponse.body().asString());
    }

    /**
     * 获取客户列表
     */
    public void customerList(int pagesize, int pagenumber, String keywords) {
        given()
                //.proxy("127.0.0.1", 8888)
                .baseUri("http://127.0.0.1")
                .cookies(cookies)
                .queryParam("action", "list_customer")
                .queryParam("pagesize", pagesize)
                .queryParam("pagenum", pagenumber)
                .queryParam("keywords", keywords)
                .when()
                .get("/api/mgr/customers")
                .then()
                .log().all()
                .statusCode(200);
    }

    public void addCustomer(String name, String phonenumber, String address) {
        Map<String, Object> customer = new HashMap<>();
        customer.put("action", "add_customer");

        //List<Map<String, String>> data = new ArrayList<>();
        Map<String, String> customerData = new HashMap<>();
        customerData.put("name", name);
        customerData.put("phonenumber", phonenumber);
        customerData.put("address", address);
        //data.add(customerData);

        customer.put("data", customerData);

        //调用gson转化成json格式
        String jsonPayload = new Gson().toJson(customer);

        given()
                .baseUri("http://127.0.0.1")
                .cookies(cookies)
                .header("Content-Type", "application/json")
                .body(jsonPayload)
                .when()
                .post("/api/mgr/customers")
                .then()
                .log().all()
                .statusCode(200);

    }

    public void modifyCustomer(int id, String name, String phonenumber, String address) {
        Map<String, Object> customer = new HashMap<>();
        customer.put("action", "modify_customer");
        customer.put("id", id);
        Map<String, String> customerData = new HashMap<>();
        customerData.put("name", name);
        customerData.put("phonenumber", phonenumber);
        customerData.put("address", address);
        customer.put("newdata", customerData);

        String jsonPayload = new Gson().toJson(customer);

        given()
                .baseUri("http://127.0.0.1")
                .cookies(cookies)
                .header("Content-Type", "application/json")
                .body(jsonPayload)
                .when()
                .put("/api/mgr/customers")
                .then()
                .log().all()
                .statusCode(200);
    }

    public void deleteCustomer(int id) {
        Map<String, Object> customer = new HashMap<>();
        customer.put("action", "del_customer");
        customer.put("id", id);

        String jsonPayload = new Gson().toJson(customer);

        given()
                .baseUri("http://127.0.0.1")
                .cookies(cookies)
                .header("Content-Type", "application/json")
                .body(jsonPayload)
                .when()
                .delete("/api/mgr/customers")
                .then()
                .log().all()
                .statusCode(200);
    }
    // 这个方法会自动成为测试方法
    public void testAPIMgr() {
        APIMgr apiMgr = new APIMgr();
        apiMgr.mgrLogin("byhy", "88888888");
        apiMgr.customerList(20, 1, "");
        //apiMgr.addCustomer("测试客户", "12345678901", "北京123");//id 24
        //apiMgr.modifyCustomer(24, "羊宫妃那", "9876512", "东京");
        //apiMgr.deleteCustomer(23);
    }
}
