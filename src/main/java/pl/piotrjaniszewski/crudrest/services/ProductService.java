package pl.piotrjaniszewski.crudrest.services;

import pl.piotrjaniszewski.crudrest.domain.Product;

import java.util.List;

public interface ProductService {
    void deleteById(Long id);
    Product saveRecipe(Product product);
    Product findProductById(Long id);
    List<Product> findAllProducts();
}