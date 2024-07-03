/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ACER
 */
public class ProductsBLO implements Serializable, Accessible<Products>{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Product-ManagementPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public int insertRec(Products obj) {
        EntityManager em = emf.createEntityManager();
        Products newProducts = em.find(Products.class, obj.getProductId());
        int result = 0;
        
        if(newProducts == null){
            newProducts = obj;
            em.getTransaction().begin();
            em.persist(newProducts);
            em.getTransaction().commit();
            result = 1;
        }
        return result;
    }

    @Override
    public int updateRec(Products obj) {
        EntityManager em = emf.createEntityManager();
        Products updateProduct = em.find(Products.class, obj.getProductId());
        int result = 0;
        
        if(updateProduct != null){
            updateProduct.setProductName(obj.getProductName());
            updateProduct.setBrief(obj.getBrief());
            updateProduct.setPostedDate(obj.getPostedDate());
            updateProduct.setTypeId(obj.getTypeId());
            updateProduct.setAccount(obj.getAccount());
            updateProduct.setUnit(obj.getUnit());
            updateProduct.setPrice(obj.getPrice());
            updateProduct.setDiscount(obj.getDiscount());
            
            em.getTransaction().begin();
            em.merge(updateProduct);
            em.getTransaction().commit();
                      
            result = 1;
        }
        return result;
    }

    public int updatePhoto(Products obj){
        EntityManager em = emf.createEntityManager();
        Products updateProduct = em.find(Products.class, obj.getProductId());
        int result = 0;
        
        if(updateProduct != null){
            updateProduct.setProductImage(obj.getProductImage());
           
            em.getTransaction().begin();
            em.merge(updateProduct);
            em.getTransaction().commit();

            result = 1;
        }
        return result;
    }
    @Override
    public int deleteRec(Products obj) {
        EntityManager em = emf.createEntityManager();
        Products delProduct = em.find(Products.class, obj.getProductId());
        int result = 0;
        
        if (delProduct != null) {
            em.getTransaction().begin();
            em.remove(delProduct);
            result = 1;
            em.getTransaction().commit();
        }
        
        return result;
    }

     public int deleteRec(String id) {
        EntityManager em = emf.createEntityManager();
         Products delProduct = em.find(Products.class, id);
        int result = 0;
        
        if (delProduct != null) {
            em.getTransaction().begin();
            em.remove(delProduct);
            result = 1;
            em.getTransaction().commit();
        }
        
        return result;
    }
    @Override
    public Products getObjectById(String id) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT p FROM Products p "
                + "WHERE p.productId = :productId";
        Query query = em.createQuery(jpql);
        query.setParameter("productId", id);
        Products product = null;
        try {
            product = (Products)query.getSingleResult();
        } catch (NoResultException ex) {
            Logger.getLogger(ProductsBLO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    @Override
    public List<Products> listAll() {
        EntityManager em = emf.createEntityManager();
        String jpql = "Products.findAll"; 
        Query query = em.createNamedQuery(jpql);
        
        List<Products> list = null;
        try {
            list = query.getResultList();
        } catch (NoResultException ex) {
            Logger.getLogger(ProductsBLO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public List<Products> listByName() {
        EntityManager em = emf.createEntityManager();
        String jpql = "Products.findByProductName"; 
        Query query = em.createNamedQuery(jpql);
        
        List<Products> list = null;
        try {
            list = query.getResultList();
        } catch (NoResultException ex) {
            Logger.getLogger(ProductsBLO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
      public List<Products> listByCategory(int id) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT p FROM Products p "
                + "WHERE p.type = :type"; 
        Query query = em.createQuery(jpql);
        query.setParameter("type", id);
        
        List<Products> list = null;
        try {
            list = query.getResultList();
        } catch (NoResultException ex) {
            Logger.getLogger(ProductsBLO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
