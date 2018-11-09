package pl.piotrjaniszewski.crudrest.services;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import pl.piotrjaniszewski.crudrest.domain.User;

import java.util.List;

@Service
public interface UserService {
    void deleteById(Long id);
    User saveUser(User user);
    User findUserById(Long id);
    List<User> findAllUsers();
    User findUserByUsername(String username);
}
