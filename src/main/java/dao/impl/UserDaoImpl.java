package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

@Repository
public class UserDaoImpl implements UserDao {

    private Logger logger = Logger.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDaoImpl(SessionFactory sessionFactory, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.sessionFactory = sessionFactory;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM User", User.class).list();
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        session.save(user);
        logger.info("this user was added to the data");
    }

    @Override
    public Optional<User> getUserByMail(String mail) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE mail =:mail");
        query.setParameter("mail", mail).setMaxResults(1);
        User user = (User) query.uniqueResult();
        logger.info("this user is present");
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        User newUser = session.get(User.class, user.getId());
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setMail(user.getMail());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        session.update(newUser);
    }

    @Override
    public void remove(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(user);
    }
}
