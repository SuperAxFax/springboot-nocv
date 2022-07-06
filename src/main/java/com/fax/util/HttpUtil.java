package com.fax.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**完成对外部疫情数据接口的获取
 * 步骤：
 *    1：直接获取请求的api地址
 *    2：编写请求的相关配置并执行请求
 *    3：根据状态码解析获取的数据成json格式
 *    4：关闭打开的请求
 */
@Component
public class HttpUtil {

    public String httputil() throws Exception {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000000)
                .setConnectionRequestTimeout(100000)
                .setSocketTimeout(100000)
                .build();

        //1：直接获取请求的api地址
        HttpGet httpGet = new HttpGet("https://c.m.163.com/ug/api/wuhan/app/data/list-total");
        //2：编写的相关配置并执行请求
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = HttpClients.createDefault().execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();
        String out = null;
        if(statusCode == 200){
            HttpEntity entity = response.getEntity();
            out = EntityUtils.toString(entity, "utf-8");
            System.out.println(out);
        }
        if (httpGet!=null){
            httpGet.clone();
        }
        if (response != null){
            response.close();
        }

        return out;
    }
}
