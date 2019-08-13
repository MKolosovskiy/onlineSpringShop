package controller;

import model.Basket;
import model.Order;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BasketService;
import service.MailService;
import service.OrderService;
import service.impl.MailServiceImpl;
import utils.CodeGenerator;
import java.util.Optional;

@Controller
@RequestMapping("/user/order")
public class OrderController {

    private final BasketService basketService;
    private final OrderService orderService;

    @Autowired
    public OrderController(BasketService basketService, OrderService orderService) {
        this.basketService = basketService;
        this.orderService = orderService;
    }

    @GetMapping
    public String order(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("name", user.getName());
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("mail", user.getMail());
        return "order";
    }

    @PostMapping
    public String order(@RequestParam("name") String name,
                        @RequestParam("surname") String surname,
                        @RequestParam("mail") String mail,
                        @RequestParam("phoneNumber") String phoneNmber,
                        @RequestParam("address") String address,
                        @AuthenticationPrincipal User user){
        String code = CodeGenerator.codeGenerator();
        MailService mailService = new MailServiceImpl();
        mailService.sendCode(code, mail);
        Optional<Basket> optionalBasket = basketService.getBasketByUser(user);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            Order order = new Order(name, surname, mail, phoneNmber, address, code, basket);
            orderService.addOrder(order);
        }
        return "redirect:/code/enter";
    }
}
