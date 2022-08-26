package entityControllers;

import entities.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class UserDaoCtrl implements Serializable
{
    private EntityManagerFactory emf = null;
    private UserEntity userEntity;

    public UserDaoCtrl (EntityManagerFactory emf) { this.emf = emf; }
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(UserEntity userEntity)
    {
        EntityManager em = null;

        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(userEntity);
            et.commit();
        }
        catch (RuntimeException e) { em.getTransaction().rollback(); }

        finally { em.close(); }
    }

    public void update(UserEntity userEntity)
    {
        EntityManager em = null;

        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(userEntity);
            et.commit();
        }
        catch (RuntimeException e) { em.getTransaction().rollback(); }

        finally { em.close(); }
    }

    public void remove(UserEntity userEntity, int entityId)
    {
        EntityManager em = getEntityManager();

        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            //em.remove((UserEntity) em.find(this.entityClass, entityId));
            et.commit();
        }
        catch (RuntimeException e) { em.getTransaction().rollback(); }

        finally { em.close(); }
    }

    public UserEntity find(int id)
    {
        EntityManager em = getEntityManager();

        try {
            //final T t = em.find(this.entityClass, id);
            //return userEntity;
        }
        catch (RuntimeException e) { em.getTransaction().rollback(); }

        finally { em.close(); }

        return null;
    }


    public List<UserEntity> findAll()
    {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            //cq.select(cq.from(entityClass));
            //return (List<userEntityObj>) em.createQuery(cq).getResultList();
        }
        catch (RuntimeException e) { em.getTransaction().rollback(); }

        finally { em.close(); }

        return null;
    }
}
