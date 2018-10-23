package pl.piotrjaniszewski.crudrest.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.piotrjaniszewski.crudrest.domain.Product;
import pl.piotrjaniszewski.crudrest.domain.ProductList;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;

    private final String Url="http://localhost:8080/api/product/";
    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        return restTemplate.getForObject(Url+"all" , ProductList.class).getList();
    }

    @Override
    public Product getProductById(Long id) {
        return restTemplate.getForObject(Url+id,Product.class);
    }

    @Override
    public Product createNewProduct(Product product) {
        return restTemplate.postForObject(Url+"create",product,Product.class);
    }

    @Override
    public void deleteProductById(Long id) {
        restTemplate.delete(Url+id+"/delete");
    }

    @Override
    public Product patchProductById(Long id, Product product) {
        return restTemplate.patchForObject(Url+id+"/patch",product,Product.class);
    }
}