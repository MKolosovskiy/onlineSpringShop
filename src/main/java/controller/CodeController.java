package controller;

import model.Order;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.OrderService;
import java.util.Optional;

@Controller
@RequestMapping("/code/enter")
public class CodeController {

    private final OrderService orderService;

    @Autowired
    public CodeController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String code() {
        return "user_enter_code";
    }

    @PostMapping
    public String code(@RequestParam("code") String userCode,
                       @AuthenticationPrincipal User user) {
        Optional<Order> optionalOrder = orderService.getOrderbyUser(user);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (userCode.equals(order.getCode())) {
                orderService.confirmOrder(order);
                return "redirect:/user/product/buy";
            }
        }
        return "redirect:/code/enter";
    }
}
