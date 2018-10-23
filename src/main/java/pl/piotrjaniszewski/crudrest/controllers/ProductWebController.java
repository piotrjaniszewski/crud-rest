package pl.piotrjaniszewski.crudrest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.piotrjaniszewski.crudrest.domain.Product;
import pl.piotrjaniszewski.crudrest.services.ApiService;

@Controller
public class ProductWebController {

    ApiService apiService;

    public ProductWebController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("products", apiService.getAllProducts());
        return "index";
    }

    @GetMapping("/product/{id}/show")
    public String showProduct(Model model, @PathVariable Long id){
        model.addAttribute("product",apiService.getProductById(id));
        model.addAttribute("url","/product/"+id+"/update");
        return "product";
    }

    @PostMapping("/product/{id}/update")
    public String updateProduct(@ModelAttribute("product") Product product, @PathVariable Long id){
        apiService.patchProductById(id,product);
        return "redirect:/index";
    }

    @GetMapping({"/product/new"})
    public String newProduct(Model model){
        Product product = new Product();
        model.addAttribute("product",product);
        model.addAttribute("url","/product/save");
        return "product";
    }

    @PostMapping("/product/save")
    public String saveNewProduct(@ModelAttribute("product") Product product){
        apiService.createNewProduct(product);
        return "redirect:/index";
    }

    @GetMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Long id){
        apiService.deleteProductById(id);
        return "redirect:/index";
    }
}
