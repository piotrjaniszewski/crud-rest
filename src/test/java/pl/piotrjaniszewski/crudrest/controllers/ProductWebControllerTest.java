package pl.piotrjaniszewski.crudrest.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import pl.piotrjaniszewski.crudrest.domain.Product;
import pl.piotrjaniszewski.crudrest.services.ApiService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductWebControllerTest {

    @Mock
    ApiService apiService;

    @Mock
    Model model;

    ProductWebController productWebController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productWebController = new ProductWebController(apiService);
    }

    @Test
    public void index() {
        List<Product> products = Arrays.asList(new Product(), new Product(), new Product());
        when(apiService.getAllProducts()).thenReturn(products);

        String viewName = productWebController.index(model);
        assertEquals(viewName,"index");

        ArgumentCaptor<List<Product>> productArgumentCaptor = ArgumentCaptor.forClass(List.class);

        verify(apiService,times(1)).getAllProducts();
        verify(model,times(1)).addAttribute(eq("products"),productArgumentCaptor.capture());
        verify(model,times(1)).addAttribute(anyString(),any());

        assertEquals(3,productArgumentCaptor.getValue().size());
    }
}