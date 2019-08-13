package service.impl;

import dao.BasketDao;
import model.Basket;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BasketService;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketDao basketDao;

    @Autowired
    public BasketServiceImpl(BasketDao basketDao) {
        this.basketDao = basketDao;
    }

    @Override
    @Transactional
    public void addBasket(Basket basket) {
        basketDao.addBasket(basket);
    }

    @Override
    @Transactional
    public Optional<Basket> getBasketByUser(User user) {
        return basketDao.getBasketByUser(user);
    }

    @Override
    @Transactional
    public void addProductToBasket(Basket basket) {
     basketDao.addProductToBasket(basket);
    }
}
