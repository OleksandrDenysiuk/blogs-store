package com.portfolio.blogsstore.service;

import com.portfolio.blogsstore.domain.Role;
import com.portfolio.blogsstore.domain.User;
import com.portfolio.blogsstore.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateProfile(User oldUser, User form) {

        boolean isUserNameChanged = isChanged(oldUser.getUsername(), form.getUsername());
        boolean isFirstNameChanged = isChanged(oldUser.getFirstName(), form.getFirstName());
        boolean isLastNameChanged = isChanged(oldUser.getLastName(), form.getLastName());
        boolean isEmailChanged = isChanged(oldUser.getEmail(), form.getEmail());
        boolean isAddressChanged = isChanged(oldUser.getAddress(), form.getAddress());

        if (isUserNameChanged) {
            oldUser.setUsername(form.getUsername());
        }
        if (isFirstNameChanged) {
            oldUser.setFirstName(form.getFirstName());
        }
        if (isLastNameChanged) {
            oldUser.setLastName(form.getLastName());
        }
        if (!StringUtils.isEmpty(form.getPassword())) {
            oldUser.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
        }
        if (isEmailChanged) {
            oldUser.setEmail(form.getEmail());
        }
        if (isAddressChanged) {
            oldUser.setAddress(form.getAddress());
        }

        return userRepository.save(oldUser);
    }

    @Override
    public void subscribe(User subscriber, User user) {
        user.getSubscribers().add(subscriber);
        userRepository.save(user);
    }

    @Override
    public void unsubscribe(User subscriber, User user) {
        user.getSubscribers().remove(subscriber);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    private boolean isChanged(String value1 , String value2){
        return (value1 != null && !value1.equals(value2)) ||
                (value2 != null && !value2.equals(value1));
    }
}
