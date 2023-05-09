package com.tutrit.springdata.service;

import com.tutrit.springdata.entity.User;
import com.tutrit.springdata.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCrudService implements UserService {

    private final UserRepository userRepository;

    public UserCrudService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElse(new User());
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public void update(User user,Long id){
        User findUser = userRepository.findById(id).orElse(new User());
        findUser.setName(user.getName());
        findUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(findUser);
    }

}
