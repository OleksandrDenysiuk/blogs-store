package com.portfolio.blogsstore.repository;

import com.portfolio.blogsstore.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);
}
