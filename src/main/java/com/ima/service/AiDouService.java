package com.ima.service;

import com.ima.model.IDouChange;
import com.ima.model.User;
import com.ima.repository.AiDouChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/3/21.
 */
@Service
public class AiDouService {
    @Autowired
    private AiDouChangeRepository aiDouChangeRepository;
    public void save(IDouChange iDouChange){
        aiDouChangeRepository.save(iDouChange);
    }
    //注册给100
    public void registerCount(User user ){
        IDouChange iDouChange = new IDouChange();
        int registerCount = 300;
        String type = "注册成功";
        iDouChange.setChangeType(type);
        iDouChange.setiDouCount(registerCount);
        iDouChange.setUser(user);
        aiDouChangeRepository.save(iDouChange);
    }
    public List<IDouChange> getHistory(Long id){
        List<IDouChange> iDouChangeList = aiDouChangeRepository.findById(id);
        return  iDouChangeList;
    }
}
