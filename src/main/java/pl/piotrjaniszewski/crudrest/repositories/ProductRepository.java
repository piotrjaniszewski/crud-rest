package pl.piotrjaniszewski.crudrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.crudrest.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}