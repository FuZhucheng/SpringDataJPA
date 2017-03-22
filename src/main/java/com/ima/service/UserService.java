package com.ima.service;

import com.ima.model.User;
import com.ima.repository.UserRepository;
import com.ima.utils.CreateIMAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ${符柱成} on 2017/3/9.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public User login(String account) {
        User user = userRepository.getByAccount(account);
        System.out.println("userId   :" + user);
        return user;
    }
    //注册
    public User register(String account, String name, String sex)  {
        boolean flag;
        User user = new User();
        if (account == null) {
            return null;
        } else {
            //判存在
            if (userRepository.getByAccount(account) != null) {
                return null;
            } else {
                user.setAccount(account);
                user.setName(name);
                user.setSex(sex);
                //注册成功加爱豆
                user.setiDouCount((long)300);
                user.setChargeMoney((long)0);
                userRepository.save(user);
                //注册云旺，想体验的就自己打开这个咯
//                CreateIMAccount.createIMAccount(account);
                return user;
            }
        }
    }
    //更新昵称
    public boolean updateName(Long userId, String name) {
        boolean flag = false;
        if (userId != null && name != null) {
            userRepository.updateName(name, userId);
            flag = true;
        }
        return flag;
    }

}
