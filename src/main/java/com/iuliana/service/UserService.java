package com.iuliana.service;

import com.iuliana.dao.UserDao;
import com.iuliana.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Set<User> getAllUsers() {
        try(Session session = sessionFactory.openSession()){
            return new HashSet<>(session.createQuery("FROM User").list());
        }
    }

    @Override
    public User getUserById(int id) {
        try(Session session = sessionFactory.openSession()){
            return session.find(User.class, id);
        }
    }

    @Override
    public int deleteUserById(int id) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(getUserById(id));
            transaction.commit();
        } catch (Exception e){
            return -1;
        }
        return 0;
    }

    @Override
    public int insertUser(User user) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e){
            return -1;
        }
        return 0;
    }

    @Override
    public int updateUser(User user) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e){
            return -1;
        }
        return 0;
    }
}
