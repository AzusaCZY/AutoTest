package com.api;

import org.testng.annotations.Test;

@Test
public class TestApi2 {


    public void testLogin1(){
        APIMgr apiMgr = new APIMgr();
        apiMgr.mgrLogin("byhy", "88888888");
    }

    public void testLogin2(){
        APIMgr apiMgr = new APIMgr();
        apiMgr.mgrLogin(null, "88888888");
    }

    public void testLogin3(){
        APIMgr apiMgr = new APIMgr();
        apiMgr.mgrLogin("byhy",null );
    }

    public void testLogin4(){
        APIMgr apiMgr = new APIMgr();
        apiMgr.mgrLogin("byhy","12345678");
    }

    public void testLogin5(){
        APIMgr apiMgr = new APIMgr();
        apiMgr.mgrLogin("ygfn", "88888888");
    }

    public void deleteCustomer(){
        APIMgr apiMgr = new APIMgr();
        apiMgr.mgrLogin("byhy", "88888888");
        for (int i = 1; i <= 25; i++){
            apiMgr.deleteCustomer(i);
        }
    }

    public void testCustomerList(){
        APIMgr apiMgr = new APIMgr();
        apiMgr.mgrLogin("byhy", "88888888");
        apiMgr.customerList(10, 1, "");
    }

    public void testAddCustomer(){
        APIMgr apiMgr = new APIMgr();
        apiMgr.mgrLogin("byhy", "88888888");
        for (int i = 1; i <= 10; i++) {
            apiMgr.addCustomer("测试客户" + i, "123456789" + i, "北京" +  i);
        }
        apiMgr.customerList(20, 1, "");
    }
}
