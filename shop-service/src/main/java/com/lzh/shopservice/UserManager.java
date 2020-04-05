package com.lzh.shopservice;

import com.lzh.shopcommon.page.Page;
import com.lzh.shop.dao.UserDao;
import com.lzh.shopentity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserManager {
    @Autowired
    private UserDao userDao;
    public User getUser(Long userId) {
        return userDao.get(userId);
    }


    public void insert(User user) {
        userDao.insert(user);
    }

    public Page<User> listUserByPartyId(Page<User> page, Map<String, Object> filters) {
        return userDao.findPage(page,"", filters);
    }
    public void update(User user) {
        userDao.update(user);
    }
}
