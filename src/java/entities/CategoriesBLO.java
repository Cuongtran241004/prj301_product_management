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
public class CategoriesBLO implements Serializable, Accessible<Categories> {

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
    public int insertRec(Categories obj) {
        EntityManager em = emf.createEntityManager();
        Categories newCate = em.find(Categories.class, obj.getCategoryName());
        int result = 0;

        if (newCate == null) {
            em.getTransaction().begin();
            em.persist(newCate);
            em.getTransaction().commit();
            result = 1;
        }
        em.close();
        return result;
    }

    @Override
    public int updateRec(Categories obj) {
        EntityManager em = emf.createEntityManager();
        Categories updateCate = em.find(Categories.class, obj.getTypeId());
        int result = 0;

        if (updateCate != null) {
            updateCate.setCategoryName(obj.getCategoryName());
            updateCate.setMemo(obj.getMemo());

            em.getTransaction().begin();
            em.merge(updateCate);
            em.getTransaction().commit();
            result = 1;
        }
        em.close();
        return result;
    }

    @Override
    public int deleteRec(Categories obj) {
        EntityManager em = emf.createEntityManager();
        Categories deleteCate = em.find(Categories.class, obj.getTypeId());
        int result = 0;

        if (deleteCate != null) {
            em.getTransaction().begin();
            em.remove(deleteCate);
            em.getTransaction().commit();
            result = 1;
        }
        em.close();

        return result;
    }

    public int deleteRec(String id) {
        EntityManager em = emf.createEntityManager();
        Categories deleteCate = em.find(Categories.class, id);
        int result = 0;

        if (deleteCate != null) {
            em.getTransaction().begin();
            em.remove(deleteCate);
            em.getTransaction().commit();
            result = 1;
        }
        em.close();

        return result;
    }

    @Override
    public Categories getObjectById(String id) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT c FROM Categories c "
                + "WHERE c.typeId = :typeId";
        Query query = em.createQuery(jpql);
        query.setParameter("typeId", Integer.parseInt(id));

        Categories cate = null;
        try {
            cate = (Categories) query.getSingleResult();
        } catch (NoResultException ex) {
            Logger.getLogger(CategoriesBLO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
        return cate;
    }

    public Categories getObjectById(int id) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT c FROM Categories c "
                + "WHERE c.typeId = :typeId";
        Query query = em.createQuery(jpql);
        query.setParameter("typeId", id);

        Categories cate = null;
        try {
            cate = (Categories) query.getSingleResult();
        } catch (NoResultException ex) {
            Logger.getLogger(CategoriesBLO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
        return cate;
    }

    public Categories getCateByName(String name) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT c FROM Categories c "
                + "WHERE c.categoryName = :categoryName";
        Query query = em.createQuery(jpql);
        query.setParameter("categoryName", name);

        Categories cate = null;
        try {
            cate = (Categories) query.getSingleResult();

        } catch (NoResultException ex) {
            Logger.getLogger(CategoriesBLO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
        return cate;
    }

    @Override
    public List<Categories> listAll() {
        EntityManager em = emf.createEntityManager();
        String jpql = "Categories.findAll";
        Query query = em.createNamedQuery(jpql);

        List<Categories> list = null;
        try {
            list = query.getResultList();

        } catch (NoResultException ex) {
            Logger.getLogger(CategoriesBLO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return list;
    }

    public List<Categories> listByCateName() {
        EntityManager em = emf.createEntityManager();
        String jpql = "Categories.findByCategoryName";
        Query query = em.createNamedQuery(jpql);

        List<Categories> list = null;
        try {
            list = query.getResultList();

        } catch (NoResultException ex) {
            Logger.getLogger(CategoriesBLO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return list;
    }
}
