package pl.piotrjaniszewski.crudrest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.piotrjaniszewski.crudrest.domain.Product;
import pl.piotrjaniszewski.crudrest.services.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
    }

    @Test
    public void findProductById() throws Exception {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("IMIE");

        when(productService.findProductById(anyLong())).thenReturn(product);

        mockMvc.perform(get(ProductController.BASE_URL + productId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id",equalTo(1)));
    }

    @Test
    public void findAllProducts() throws Exception {
        List<Product> productList = Arrays.asList(new Product(),new Product(), new Product());

        when(productService.findAllProducts()).thenReturn(productList);

        mockMvc.perform(get(ProductController.BASE_URL+"all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list",hasSize(3)));
    }

    @Test
    public void deleteProductById() throws Exception{
        mockMvc.perform(delete(ProductController.BASE_URL+"1/delete")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).deleteById(anyLong());
    }

    @Test
    public void patchProduct() throws Exception{
        Product product = new Product();
        product.setId(1L);
        product.setName("GUWNO");
        product.setCategory("Smierdzi");

        when(productService.saveProduct(ArgumentMatchers.any())).thenReturn(product);

        mockMvc.perform(patch(ProductController.BASE_URL+"1/patch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo("GUWNO")))
                .andExpect(jsonPath("$.category",equalTo("Smierdzi")));
    }

    @Test
    public void putProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("GUWNO");
        product.setCategory("Smierdzi");

        when(productService.saveProduct(ArgumentMatchers.any())).thenReturn(product);

        mockMvc.perform(put(ProductController.BASE_URL+"1/put")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo("GUWNO")))
                .andExpect(jsonPath("$.category",equalTo("Smierdzi")));
    }

    @Test
    public void createProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("GUWNO");
        product.setCategory("Smierdzi");

        when(productService.saveProduct(ArgumentMatchers.any())).thenReturn(product);

        mockMvc.perform(post(ProductController.BASE_URL+"create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",equalTo("GUWNO")))
                .andExpect(jsonPath("$.category",equalTo("Smierdzi")));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}