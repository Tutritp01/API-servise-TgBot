package com.tutrit.jdbc.service;


import com.tutrit.jdbc.entity.User;
import com.tutrit.jdbc.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserJDBCService implements DTOService {

    private final UserRepository userRepository;

    public UserJDBCService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public void updateUser(User user) {
        userRepository.update(user);
    }
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
