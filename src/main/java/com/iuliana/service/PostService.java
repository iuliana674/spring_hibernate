package com.iuliana.service;

import com.iuliana.dao.PostDao;
import com.iuliana.model.Category;
import com.iuliana.model.Post;
import com.iuliana.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PostService implements PostDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insertPost(Post post) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
        }
    }

    @Override
    public Post getPostById(int id) {
        try(Session session = sessionFactory.openSession()){
            return session.find(Post.class, id);
        }
    }

    @Override
    public Set<Post> getAllPosts() {
        try(Session session = sessionFactory.openSession()){
            return new HashSet<>(session.createQuery("FROM Post").list());
        }
    }

    @Override
    public Set<Post> getPostsByCategory(Category category) {
        try(Session session = sessionFactory.openSession()){
            return new HashSet<>
                (session.createQuery("FROM Post WHERE category = :category")
                        .setParameter("category", category).list());
        }
    }

    @Override
    public Set<Post> getPostsByUser(User user) {
        try(Session session = sessionFactory.openSession()){
            return user.getPosts();
        }
    }

    @Override
    public void updatePost(Post post) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(post);
            transaction.commit();
        }
    }

    @Override
    public void deletePostById(int id) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(getPostById(id));
            transaction.commit();
        }
    }
}
