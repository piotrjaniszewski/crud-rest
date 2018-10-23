package pl.piotrjaniszewski.crudrest.services;

import org.springframework.stereotype.Service;
import pl.piotrjaniszewski.crudrest.domain.Product;
import pl.piotrjaniszewski.crudrest.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product saveRecipe(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        } else {
            throw new RuntimeException("Product Not Found!");
        }
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
