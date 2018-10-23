package pl.piotrjaniszewski.crudrest.services;

import pl.piotrjaniszewski.crudrest.domain.Product;

import java.util.List;

public interface ApiService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createNewProduct(Product product);
    void deleteProductById(Long id);
    Product patchProductById(Long id, Product product);
}
