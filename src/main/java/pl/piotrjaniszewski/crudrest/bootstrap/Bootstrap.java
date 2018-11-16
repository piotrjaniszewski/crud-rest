package pl.piotrjaniszewski.crudrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piotrjaniszewski.crudrest.domain.Product;
import pl.piotrjaniszewski.crudrest.domain.Role;
import pl.piotrjaniszewski.crudrest.domain.User;
import pl.piotrjaniszewski.crudrest.repositories.ProductRepository;
import pl.piotrjaniszewski.crudrest.repositories.UserRepository;

import java.util.LinkedList;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Bootstrap(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadProducts();
    }

    private void loadProducts(){
        Product p1 = new Product();
        p1.setName("Ketchup łagodny");
        p1.setPrice(4.5);
        p1.setQuantity(25);
        p1.setCategory("Keczupy");
        p1.setProducer("Pudliszki");

        Product p2 = new Product();
        p2.setName("Ketchup pikantny");
        p2.setPrice(5d);
        p2.setQuantity(14);
        p2.setCategory("Keczupy");
        p2.setProducer("Zeinz");

        Product p3 = new Product();
        p3.setName("Ketchup łagodny");
        p3.setPrice(4.30d);
        p3.setQuantity(21);
        p3.setCategory("Keczupy");
        p3.setProducer("Kotlin");

        Product p4 = new Product();
        p4.setName("Musztarda sarepska");
        p4.setPrice(3.30d);
        p4.setQuantity(28);
        p4.setCategory("Musztardy");
        p4.setProducer("Kamis");

        Product p5 = new Product();
        p5.setName("Musztarda Dijon");
        p5.setPrice(5.20d);
        p5.setQuantity(11);
        p5.setCategory("Musztardy");
        p5.setProducer("Develey");

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
        productRepository.save(p5);

        User user = new User();
        user.setUsername("user1");
        user.setPassword("p1");
        Role role = new Role();
        role.setName("USER");
        List<Role> roles = new LinkedList<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        System.out.println("Products loaded: "+productRepository.findAll().size());
        System.out.println("Users loaded:    "+userRepository.findAll().size());
        User u1 = userRepository.findByUsername("user1").get();
        System.out.println(u1.getUsername()+" "+u1.getPassword());
    }
}