package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.repository.UserRepositoryImpl;
import user.service.model.User;

@Service
public class UserService {

    @Autowired
    UserRepositoryImpl userRepository;

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public void save(User user){
        userRepository.save(user);
    }
}
