package service.impl;

import dao.OrderDao;
import model.Order;
import model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.OrderService;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao, SessionFactory sessionFactory) {
        this.orderDao = orderDao;
    }

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    @Transactional
    public Optional<Order> getOrderbyUser(User user) {
        return orderDao.getOrderbyUser(user);
    }

    @Override
    @Transactional
    public void confirmOrder(Order order) {
        orderDao.confirmOrder(order);
    }
}
