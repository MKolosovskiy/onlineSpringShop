package dao.impl;

import dao.BasketDao;
import dao.OrderDao;
import model.Basket;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {

    private Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
        logger.info("this order was added");
    }

    @Override
    public Optional<Order> getOrderbyUser(User user) {
        try {

            Session session = sessionFactory.getCurrentSession();
            Query basketQuery = session.createQuery("FROM Basket WHERE user =:user ORDER BY id DESC");
            basketQuery.setParameter("user", user);
            Basket basket = (Basket) basketQuery.list().get(0);
            Query orderQuery = session.createQuery("FROM Order WHERE basket =:basket ORDER BY id DESC");
            orderQuery.setParameter("basket", basket);
            Order order = (Order) orderQuery.list().get(0);
            return Optional.ofNullable(order);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public void confirmOrder(Order order) {
        order.setConfirm(true);
        Session session = sessionFactory.getCurrentSession();
        session.update(order);
        logger.info("this order was confirmed");
    }
}
