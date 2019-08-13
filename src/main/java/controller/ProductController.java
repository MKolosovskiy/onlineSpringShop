package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ProductService;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct() {
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("price") Double price) {

        Product product = new Product(name, description, price);
        productService.addProduct(product);
        return "redirect:/admin/product/all";
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "all_products";
    }

    @GetMapping("/edit")
    public String editProduct(@RequestParam("id") Long id, Model model) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            model.addAttribute("name", product.getName());
            model.addAttribute("description", product.getDescription());
            model.addAttribute("price", product.getPrice());
            model.addAttribute("productId", id);
        }
        return "edit_product";
    }

    @PostMapping("/edit")
    public String editProduct(@RequestParam("id") Long id,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") Double price) {

        Product product = new Product(id, name, description, price);
        productService.updateProduct(product);
        return "redirect:/admin/product/all";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("id") Long id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productService.remove(product);
        }
        return "redirect:/admin/product/all";
    }
}
