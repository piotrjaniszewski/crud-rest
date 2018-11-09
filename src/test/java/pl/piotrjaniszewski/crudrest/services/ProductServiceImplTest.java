package pl.piotrjaniszewski.crudrest.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.piotrjaniszewski.crudrest.domain.Product;
import pl.piotrjaniszewski.crudrest.repositories.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void deleteById() {

    }

    @Test
    public void saveProduct() {

    }

    @Test
    public void findProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("DUPA");
        product.setCategory("Category");

        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));

        Product testProduct = productService.findProductById(1L);

        assertEquals(product.getId(),testProduct.getId());
        assertEquals(product.getName(),testProduct.getName());

        verify(productRepository,times(1)).findById(anyLong());
    }

    @Test
    public void findAllProducts() {
        List<Product> products = Arrays.asList(new Product(), new Product(), new Product());
        Mockito.when(productRepository.findAll()).thenReturn(products);

        List<Product> testProducts = productService.findAllProducts();

        assertEquals(testProducts.size(),3);

        verify(productRepository,times(1)).findAll();
    }
}