package controller;

import model.Basket;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BasketService;
import java.util.Optional;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping
    public String basket(@AuthenticationPrincipal User user, Model model) {
        Optional<Basket> optionalBasket = basketService.getBasketByUser(user);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            basketService.getBasketByUser(user);
            basketService.addBasket(basket);
            model.addAttribute("productsInBasket", basket.getProducts());
        }
        return "basket";
    }
}
