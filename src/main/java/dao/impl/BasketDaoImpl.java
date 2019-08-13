package dao.impl;

import dao.BasketDao;
import model.Basket;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class BasketDaoImpl implements BasketDao {

    private Logger logger = Logger.getLogger(BasketDaoImpl.class);
    private final SessionFactory sessionFactory;

    public BasketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBasket(Basket basket) {
        Session session = sessionFactory.getCurrentSession();
        session.save(basket);
        logger.info("this basket was added");
    }

    @Override
    public Optional<Basket> getBasketByUser(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Basket WHERE user =:user ORDER BY id DESC");
            query.setParameter("user", user);
            Basket basket = (Basket) query.list().get(0);
            return Optional.ofNullable(basket);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public void addProductToBasket(Basket basket) {
        Session session = sessionFactory.getCurrentSession();
        session.update(basket);
        logger.info("this product was added to basket");
    }
}

