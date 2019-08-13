package service;

import model.Order;
import model.User;
import java.util.Optional;

public interface OrderService {

    void addOrder(Order order);
    Optional<Order> getOrderbyUser(User user);
    void confirmOrder(Order order);
}
