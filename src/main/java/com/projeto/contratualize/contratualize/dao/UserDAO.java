/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.contratualize.contratualize.dao;

import com.projeto.contratualize.contratualize.model.User;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Random;
import javax.persistence.TypedQuery;


/**
 *
 * @author gabri
 */
public class UserDAO extends GenericDAO<User> {
    public UserDAO(){
        super(User.class);
    }
    
    //verificar se usuário já existe
    public boolean usernameExists(String username) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
        query.setParameter("username", username);
        long count = query.getSingleResult();
        em.close();
        return count > 0;
    }
  
    //sugerir nome alternativo
    public String suggestUsername(String username) {
        Random random = new Random();
        String suggestedUsername;
        do {
            suggestedUsername = username + random.nextInt(1000);
        } while(usernameExists(suggestedUsername));
        return suggestedUsername;
    }
    
    public User findByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();
        em.close();
        return user;
    }
    
    
    //Verificar se a senha atual é a mesma que o gestor inseriu
    public boolean verifyPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
    
    //alterar a senha do gestor se a senha for validada
    public void changePassword(User user, String currentPassword, String newPassword) {
        if(verifyPassword(user, currentPassword)) {
            user.setPassword(newPassword);
            update(user);
            System.out.println("Senha alterada com sucesso!");
        } else {
            throw new IllegalArgumentException("Senha atual incorreta!");
        }
    }
    
    //Permitir o admin mudar a senha sem validação.
    public void resetPasswordByAdmin(User user, String newPassword) {
        user.setPassword(newPassword);
        update(user);
    }
    
    @Override
    public void insert(User entity) {
        if(usernameExists(entity.getUsername())) {
            throw new IllegalArgumentException("Usuário já existente. Sugestão disponível: " + suggestUsername(entity.getUsername()));
        }
        super.insert(entity);
    }
    
    public void delete(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User userToRemove = em.find(User.class, user.getId());
            if(userToRemove !=null){
                em.remove(userToRemove);
            } else {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            em.getTransaction().commit();
            
        } catch(Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally{
            em.close();
        }
    }
    
    
    public List<User> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();
        em.close();
        return users;
    }
    
}
