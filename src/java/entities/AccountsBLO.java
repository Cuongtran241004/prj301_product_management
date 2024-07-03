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
public class AccountsBLO implements Serializable, Accessible<Accounts> {

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

    public Accounts loginSuccess(String account, String pass) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT a FROM Accounts a "
                + "WHERE a.account = :account "
                + "AND a.pass = :pass ";

        Query query = em.createQuery(jpql);
        query.setParameter("account", account);
        query.setParameter("pass", pass);

        Accounts acc = null;
        try {
            acc = (Accounts) query.getSingleResult();
        } catch (NoResultException ex) {
            Logger.getLogger(AccountsBLO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    public List findAccountByFirstName(String firstName) {
        EntityManager em = emf.createEntityManager();

        String jpql = "Accounts.findByFirstName";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("firstName", "%" + firstName + "%");
        List<Accounts> list = null;

        try {
            list = query.getResultList();
        } catch (NoResultException ex) {
            Logger.getLogger(AccountsBLO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public int insertRec(Accounts obj) {
        EntityManager em = emf.createEntityManager();
        Accounts newAccount = em.find(Accounts.class, obj.getAccount());
        int result = 0;

        if (newAccount == null) {
            newAccount = obj;          
            em.getTransaction().begin();
            em.persist(newAccount);
            result = 1;
            em.getTransaction().commit();
        }
        return result;
    }

    @Override
    public int updateRec(Accounts obj) {
        EntityManager em = emf.createEntityManager();
        Accounts updateAccount = em.find(Accounts.class, obj.getAccount());
        int result = 0;

        if (updateAccount != null) {
            updateAccount.setPass(obj.getPass());
            updateAccount.setLastName(obj.getLastName());
            updateAccount.setFirstName(obj.getFirstName());
            updateAccount.setBirthday(obj.getBirthday());
            updateAccount.setGender(obj.getGender());
            updateAccount.setPhone(obj.getPhone());
            updateAccount.setIsUse(obj.getIsUse());
            updateAccount.setRoleInSystem(obj.getRoleInSystem());

            em.getTransaction().begin();
            em.merge(updateAccount);
            em.getTransaction().commit();

            result = 1;
        }
        return result;
    }

    public int updateIsUsed(String account, boolean isUse) {
        EntityManager em = emf.createEntityManager();
        Accounts updateAccount = em.find(Accounts.class, account);
        int result = 0;

        if (updateAccount != null) {
            updateAccount.setIsUse(isUse);

            em.getTransaction().begin();
            em.merge(updateAccount);
            em.getTransaction().commit();

            result = 1;
        }
        return result;
    }

    @Override
    public int deleteRec(Accounts obj) {
        EntityManager em = emf.createEntityManager();
        Accounts delAccount = em.find(Accounts.class, obj.getAccount());
        int result = 0;

        if (delAccount != null) {
            em.getTransaction().begin();
            em.remove(delAccount);
            result = 1;
            em.getTransaction().commit();
        }
        return result;
    }

    public int deleteRec(String account) {
        EntityManager em = emf.createEntityManager();
        Accounts delAccount = em.find(Accounts.class, account);
        int result = 0;

        if (delAccount != null) {
            em.getTransaction().begin();
            em.remove(delAccount);
            result = 1;
            em.getTransaction().commit();
        }
        return result;
    }

    @Override
    public Accounts getObjectById(String id) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT a FROM Accounts a "
                + "WHERE a.account = :account";

        Query query = em.createQuery(jpql);
        query.setParameter("account", id);

        Accounts acc = null;
        try {
            acc = (Accounts) query.getSingleResult();
        } catch (NoResultException ex) {
            Logger.getLogger(AccountsBLO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    @Override
    public List<Accounts> listAll() {
        EntityManager em = emf.createEntityManager();
        String jpql = "Accounts.findAll";
        Query query = em.createNamedQuery(jpql);

        List<Accounts> list = null;
        try {
            list = query.getResultList();
        } catch (NoResultException ex) {
            Logger.getLogger(AccountsBLO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<Accounts> listByRole() {
        EntityManager em = emf.createEntityManager();
        String jpql = "Accounts.findByRoleInSystem";
        Query query = em.createNamedQuery(jpql);

        List<Accounts> list = null;
        try {
            list = query.getResultList();
        } catch (NoResultException ex) {
            Logger.getLogger(AccountsBLO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
