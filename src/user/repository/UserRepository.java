package user.repository;

import user.service.model.User;

public interface UserRepository {

    User findUserByUsername(String username);

    void save(User user);
}
