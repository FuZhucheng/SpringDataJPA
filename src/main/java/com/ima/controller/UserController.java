package com.ima.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ima.dao.JedisClient;
import com.ima.dto.DTO;
import com.ima.model.IDouChange;
import com.ima.model.User;
import com.ima.service.AiDouService;
import com.ima.service.UserService;
import com.ima.utils.AuthUtil;
import com.ima.utils.JavaWebToken;
import com.ima.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户各种操作接口
 * Created by 符柱成 on 17-3-11.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AiDouService aiDouService;
    @Autowired
    private JedisClient jedisClient;

    //test：
    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    public String test(String type, Integer iDouCount )  {
        DTO dto = new DTO();
        IDouChange iDouChange = new IDouChange();
        iDouChange.setChangeType(type);
        iDouChange.setiDouCount(iDouCount);
        aiDouService.save(iDouChange);
        if (iDouChange == null) {
            dto.code = "-1";
            dto.msg = "Have bean registered";
            return JSON.toJSONString(dto);
        } else {
            return JSON.toJSONString(iDouChange);
        }
    }

    //登录
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    public String login(String account) {
        User user = userService.login(account);

        DTO dto = new DTO();
        if (user == null) {
            dto.code = "-1";
            dto.msg = "Have not registered";
        } else {
            //把用户登录信息放进Session
            Map<String, Object> loginInfo = new HashMap<>();
            loginInfo.put("userId", user.getId());
            String sessionId = JavaWebToken.createJavaWebToken(loginInfo);
            System.out.println("sessionID"+sessionId);
            dto.data = sessionId;
        }
        return JSON.toJSONString(dto);
    }

    //注册：
    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    public String register(String account, String name, String sex)  {
        User user = null;
        DTO dto = new DTO();
        user = userService.register(account, name, sex);
        aiDouService.registerCount(user);
        if (user == null) {
            dto.code = "-1";
            dto.msg = "Have bean registered";
            return JSON.toJSONString(dto);
        } else {
            return JSON.toJSONString(user);
        }
    }

    //修改昵称
    @RequestMapping(value = "/updateName", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateName(HttpServletRequest request, String name) {
        DTO dto = new DTO();
        try {
            Long userId = AuthUtil.getUserId(request);
            boolean userIsExist = userService.updateName(userId, name);
            if (userIsExist == false) {
                dto.code = "-1";
                dto.msg = "Have not updateAvatar";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(dto);
    }
    //查历史记录
    @RequestMapping(value = "/findHistory", method = {RequestMethod.GET, RequestMethod.POST})
    public String findHistory(Long id) {
        DTO dto = new DTO();
        List<IDouChange> iDouChangeList = null;
        System.out.println("jedisClient  :" + jedisClient);
        try {
            String resulthget = jedisClient.hget("个人积分记录", id + "");
            if (resulthget != null) {
                //字符串转为list
                System.out.println("有缓存啦啦啦！！！");
                JSONArray array = JSONArray.parseArray(resulthget);
                iDouChangeList = (List) array;
            } else {
                System.out.println("个人积分记录没查过");
                iDouChangeList = aiDouService.getHistory(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (iDouChangeList == null) {
            dto.code = "-1";
            dto.msg = "Have not updateAvatar";
        }
        try {
            String cacheString = JsonUtils.objectToJson(iDouChangeList);
            jedisClient.hset("个人积分记录", id + "", cacheString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(iDouChangeList);
    }
}
