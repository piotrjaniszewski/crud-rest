package pl.piotrjaniszewski.crudrest.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.piotrjaniszewski.crudrest.domain.Role;
import pl.piotrjaniszewski.crudrest.domain.User;
import pl.piotrjaniszewski.crudrest.repositories.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(s);

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;

        System.out.println();
        if(userOptional.isPresent()){
            User user = userOptional.get();
            builder = org.springframework.security.core.userdetails.User.withUsername(s);
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()).toArray(new String[]{}));
            return builder.build();
        }
        throw new UsernameNotFoundException(s);
    }
}
