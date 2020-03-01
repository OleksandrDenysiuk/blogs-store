package com.portfolio.blogsstore.service;


import com.portfolio.blogsstore.domain.User;

public interface UserService {
    User save(User user);

    User update(User user);

    User findByUsername(String username);

    User updateProfile(User oldUser, User form);

    void subscribe(User subscriber, User user);

    void unsubscribe(User subscriber, User user);
}
