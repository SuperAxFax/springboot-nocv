package com.fax.test;

import com.fax.util.HttpUtil;

public class HttpTest {
    public static void main(String[] args) throws Exception {
        HttpUtil httpUtil = new HttpUtil();
        String httputil = httpUtil.httputil();
        System.out.println(httputil);
    }
}
