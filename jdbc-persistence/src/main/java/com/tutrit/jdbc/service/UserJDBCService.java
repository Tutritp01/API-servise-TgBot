package com.tutrit.jdbc.service;


import com.tutrit.jdbc.repository.UserRepository;
import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserJDBCService implements UserService {

    private final UserRepository userRepository;

    public UserJDBCService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User getUser(String id) {
        return userRepository.findById(id);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

}
