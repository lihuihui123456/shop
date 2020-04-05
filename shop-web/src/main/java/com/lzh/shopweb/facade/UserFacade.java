package com.lzh.shopweb.facade;

import com.lzh.shopservice.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {
    @Autowired
    private UserManager userManager;
}
