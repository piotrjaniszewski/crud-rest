package pl.piotrjaniszewski.crudrest.services;

import org.springframework.stereotype.Service;
import pl.piotrjaniszewski.crudrest.domain.User;
import pl.piotrjaniszewski.crudrest.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return findUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return findAllUsers();
    }

    @Override
    public User findUserByUsername(String username) {
        return findUserByUsername(username);
    }
}
