package com.ima.utils;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.response.OpenimUsersAddResponse;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${符柱成} on 2017/3/13.
 */
public class CreateIMAccount {
    //淘宝IM默认的接口
    private static final String url ="http://gw.api.taobao.com/router/rest";
    //淘宝阿里云旺IM中，你创建的应用的key。appkey跟secret。
    private static final String appkey="";
    private static final String secret="";
    //这个是自定义的加密规则
    private static final String passwordHead = "";

    public static void createIMAccount(String account)  {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersAddRequest req = new OpenimUsersAddRequest();
        List<Userinfos> list = new ArrayList<Userinfos>();
        /*
                MD5加密
         */
        String userIMAccount = DigestUtils.md5DigestAsHex(account.getBytes());
        String userPassword = DigestUtils.md5DigestAsHex(passwordHead.concat(userIMAccount).getBytes());
        //对应云旺的用户信息
        Userinfos userinfos = new Userinfos();
        list.add(userinfos);
        userinfos.setUserid(userIMAccount);
        userinfos.setPassword(userPassword);

        req.setUserinfos(list);
        OpenimUsersAddResponse response = null;
        try {
            response = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("IM注册异常");
        }
        System.out.println("response Body"+response.getBody());
    }
}
