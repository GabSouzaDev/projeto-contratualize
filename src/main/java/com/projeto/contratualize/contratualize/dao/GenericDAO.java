/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.contratualize.contratualize.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 *
 * @author gabri
 */
public abstract class GenericDAO<T> {
    private Class<T> entityClass;
    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("contratualizePU");
    
    
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void insert(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }
    
    public T findById(Long id) {
        EntityManager em = emf.createEntityManager();
        T entity = em.find(entityClass, id);
        em.close();
        return entity;
    }
    
    public List<T> findAll() {
        EntityManager em = emf.createEntityManager();
        List<T> entities = em.createQuery("from " + entityClass.getName(), entityClass).getResultList();
        em.close();
        return entities;
    }
    
    public void update(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }
    
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        T entity = em.find(entityClass, id);
        if(entity != null) {
            em.remove(entity);
            
        }
        em.getTransaction().commit();
        em.close();
                
    }
}
