package user.repository;

import org.springframework.stereotype.Component;
import user.service.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepositoryImpl implements UserRepository {
    final private Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public User findUserByUsername(String username){
        return users.get(username);
    }

    @Override
    public void save(User user){
        users.put(user.getUsername(), user);
    }

}
