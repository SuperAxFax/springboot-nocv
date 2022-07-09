package com.fax.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fax.entity.NocvApiData;
import com.fax.service.ApiService;
import com.fax.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

@Component
public class HttpTest {
    @Autowired
    private ApiService apiService;

    @Scheduled(fixedDelay = 10000)
    public void apitest() throws Exception {

        HttpUtil httpUtil = new HttpUtil();
        String httputil = httpUtil.httputil();
        System.out.println(httputil);
    //一：从网站获取的Object对象中获得所需数据
        //1：获取json字符串中的data对象
        JSONObject jsonObject = JSON.parseObject(httputil.toString());
        Object data = jsonObject.get("data");
        System.out.println(data);

        //2：获取data中的chinaTotal对象
        JSONObject chinaTotal = JSON.parseObject(data.toString());
        Object chinaTotal1 = chinaTotal.get("chinaTotal");
        Object overseaLastUpdateTime = chinaTotal.get("overseaLastUpdateTime");
        System.out.println(overseaLastUpdateTime);
        System.out.println(chinaTotal1);

        //3：获得chinaTotal中的today数据
        JSONObject total = JSON.parseObject(chinaTotal1.toString());
        Object total1 = total.get("total");
        System.out.println(total1);

        //4：获取today中的确诊，疑似，输入等数据
        JSONObject object = JSON.parseObject(total1.toString());
        Object confirm = object.get("confirm");
        Object suspect = object.get("suspect");
        Object heal = object.get("heal");
        Object dead = object.get("dead");
        Object severe = object.get("severe");
        Object input = object.get("input");

        System.out.println(confirm);
        System.out.println(suspect);
        System.out.println(heal);
        System.out.println(dead);
        System.out.println(severe);
        System.out.println(input);
    //二：将获得的数据插入数据库
        NocvApiData nocvApiData = new NocvApiData();
        nocvApiData.setConfirm((Integer) confirm);
        nocvApiData.setSuspect((Integer) suspect);
        nocvApiData.setHeal((Integer) heal);
        nocvApiData.setDead((Integer) dead);
        nocvApiData.setSevere((Integer) severe);
        nocvApiData.setInput((Integer) input);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        nocvApiData.setDatatime(simpleDateFormat.parse(String.valueOf(overseaLastUpdateTime)));
        boolean save = apiService.save(nocvApiData);
        System.out.println(save);
    }
}
