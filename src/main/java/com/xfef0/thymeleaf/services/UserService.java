package com.xfef0.thymeleaf.services;

import com.xfef0.thymeleaf.domain.User;

public interface UserService extends CRUDService<User> {

    User findByUsername(String username);
}
