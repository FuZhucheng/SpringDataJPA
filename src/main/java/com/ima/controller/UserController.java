package com.ima.controller;

import com.alibaba.fastjson.JSON;
import com.ima.dto.DTO;
import com.ima.model.IDouChange;
import com.ima.model.User;
import com.ima.service.AiDouService;
import com.ima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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


}
