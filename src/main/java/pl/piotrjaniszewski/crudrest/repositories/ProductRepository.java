package pl.piotrjaniszewski.crudrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.crudrest.domain.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}