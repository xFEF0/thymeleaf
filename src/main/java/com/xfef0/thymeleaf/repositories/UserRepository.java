package com.xfef0.thymeleaf.repositories;

import com.xfef0.thymeleaf.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
