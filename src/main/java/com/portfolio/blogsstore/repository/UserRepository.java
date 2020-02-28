package com.portfolio.blogsstore.repository;

import com.portfolio.blogsstore.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findByUsername(String username);
}
