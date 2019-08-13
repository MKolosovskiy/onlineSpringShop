package dao.impl;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {

    private Logger logger = Logger.getLogger(ProductDaoImpl.class);
    private final SessionFactory sessionFactory;

    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Product", Product.class).list();
    }

    @Override
    public void addProduct(Product product) {
       Session session = sessionFactory.getCurrentSession();
            session.save(product);
            logger.info("this product was added");
    }

    @Override
    public Optional<Product> getProductById(Long id) {
      Session session = sessionFactory.getCurrentSession();
            Product product = session.get(Product.class, id);
            return Optional.ofNullable(product);
    }

    @Override
    public void updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
    }

    @Override
    public void remove(Product product) {
       Session session = sessionFactory.getCurrentSession();
            session.remove(product);
            logger.info("this product was removed");
    }
}
