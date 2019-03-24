package kosy.todo.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosy.todo.todo.domain.User;
import kosy.todo.todo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    
}