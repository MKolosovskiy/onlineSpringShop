package service;

import model.Basket;
import model.Product;
import model.User;

import java.util.Optional;

public interface BasketService {

    void addBasket(Basket basket);
    Optional<Basket> getBasketByUser(User user);
    void addProductToBasket(Basket basket);
}
