package controller;

import model.Basket;
import model.Product;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BasketService;
import service.ProductService;
import service.UserService;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class UserController {

    private final ProductService productService;
    private final UserService userService;
    private final BasketService basketService;

    @Autowired
    public UserController(ProductService productService, UserService userService, BasketService basketService) {
        this.productService = productService;
        this.userService = userService;
        this.basketService = basketService;
    }

    @GetMapping("/admin/user/add")
    public String addUser() {
        return "add_user";
    }

    @PostMapping("/admin/user/add")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("surname") String surname,
                          @RequestParam("mail") String mail,
                          @RequestParam("password") String password,
                          @RequestParam("repeatPassword") String repeatPassword,
                          @RequestParam("role") String role,
                          Model model) {

        if (password.equals(repeatPassword)) {
            User user = new User(name, surname, mail, password, role);
            userService.addUser(user);
            return "redirect:/admin/user/all";
        } else {
            model.addAttribute("passwordError", "Different passwords");
            model.addAttribute("name", name);
            model.addAttribute("surname", surname);
            model.addAttribute("mail", mail);
            return "add_user";
        }
    }

    @GetMapping("/admin/user/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "all_users";
    }

    @GetMapping("/admin/user/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("name", user.getName());
            model.addAttribute("surname", user.getSurname());
            model.addAttribute("mail", user.getMail());
            model.addAttribute("userId", id);
        }
        return "edit_user";
    }

    @PostMapping("/admin/user/edit")
    public String editUser(@RequestParam("id") Long id,
                           @RequestParam("name") String name,
                           @RequestParam("surname") String surname,
                           @RequestParam("mail") String mail,
                           @RequestParam("password") String password,
                           @RequestParam("repeatPassword") String repeatPassword,
                           Model model) {

        if (password.equals(repeatPassword)) {
            User user = new User(id, name, surname, mail, password);
            userService.updateUser(user);
            return "redirect:/admin/user/all";
        } else {
            model.addAttribute("passwordError", "Different passwords");
            model.addAttribute("userId", id);
            model.addAttribute("name", name);
            model.addAttribute("surname", surname);
            model.addAttribute("mail", mail);
            return "edit_user";
        }
    }

    @GetMapping("/user/product/buy")
    public String buyProduct(Model model) {
        model.addAttribute("products", productService.getAll());
        return "buy_product";
    }

    @PostMapping("/user/product/buy")
    public String buyProduct(@RequestParam("id") Long id,
                             @AuthenticationPrincipal User user,
                             Model model) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        Optional<Basket> optionalBasket = basketService.getBasketByUser(user);
        Basket basket = null;
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
        }else {
            basket = new Basket(user, new ArrayList<>());
            basketService.addBasket(basket);
        }
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            basket.getProducts().add(product);
            model.addAttribute("productCounter", basket.getProducts().size());
            basketService.addProductToBasket(basket);
        }
        model.addAttribute("products", productService.getAll());
        return "buy_product";
    }

    @GetMapping("/admin/user/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userService.remove(user);
        }
        return "redirect:/admin/user/all";
    }
}
