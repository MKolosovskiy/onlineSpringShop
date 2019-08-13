package controller;

import model.Product;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ProductService;
import service.UserService;
import javax.annotation.PostConstruct;

@Controller
public class InitController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public InitController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String login(@AuthenticationPrincipal User user) {
        if (user == null){
            return "redirect:/login";
        }
        return (user.getRole().equals("ROLE_ADMIN")) ? "redirect:/admin/user/all"
                : "redirect:/user/product/buy";
    }

    @PostConstruct
    public void init() {
        User admin = new User("1", "1", "1@net", "1", "ROLE_ADMIN");
        User user = new User("22", "2", "2@net", "2", "ROLE_USER");
        Product product = new Product("wine", "red", 20.0);
        userService.addUser(user);
        userService.addUser(admin);
        productService.addProduct(product);
    }
}
