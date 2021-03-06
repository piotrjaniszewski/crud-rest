package pl.piotrjaniszewski.crudrest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import pl.piotrjaniszewski.crudrest.domain.Product;
import pl.piotrjaniszewski.crudrest.domain.ProductList;
import pl.piotrjaniszewski.crudrest.exceptionHandlers.BasicErrorMsg;
import pl.piotrjaniszewski.crudrest.services.ProductService;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

    public static final String BASE_URL ="/api/product/";

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public ProductList findAllProducts(){
        return new ProductList( productService.findAllProducts() );
    }

    @GetMapping("loginfailed")
    public BasicErrorMsg loginFail(){
        throw new BadCredentialsException("");
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PatchMapping("{id}/patch")
    public Product patchProduct(@PathVariable Long id,@RequestBody Product product){
        product.setId(id);
        return productService.saveProduct(product);
    }

    @PutMapping("{id}/put")
    public Product putProduct(@PathVariable Long id,@RequestBody Product product){
        product.setId(id);
        return productService.saveProduct(product);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
}