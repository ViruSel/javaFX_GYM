package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class GenericDao<T>
{
    private final Class<T> entityClass;

    public GenericDao(Class<T> eClass) { this.entityClass = eClass; }

    public abstract EntityManager getEntityManager();

    public void create(T entity)
    {
        EntityManager em = getEntityManager();

        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(entity);
            et.commit();
        }
        catch (RuntimeException e) { em.getTransaction().rollback(); }

        finally { em.close(); }
    }

    public void update(T entity)
    {
        EntityManager em = getEntityManager();

        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(entity);
            et.commit();
        }
        catch (RuntimeException e) { em.getTransaction().rollback(); }

        finally { em.close(); }
    }

    public void remove(T entity, int entityId)
    {
        EntityManager em = getEntityManager();

        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.remove((T) em.find(this.entityClass, entityId));
            et.commit();
        }
        catch (RuntimeException e) { em.getTransaction().rollback(); }

        finally { em.close(); }
    }

    public T find(int id)
    {
        EntityManager em = getEntityManager();

        try {
            final T t = em.find(this.entityClass, id);
            return t;
        }
        catch (RuntimeException e) { em.getTransaction().rollback(); }

        finally { em.close(); }

        return null;
    }


    public List<T> findAll()
    {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return (List<T>) em.createQuery(cq).getResultList();
        }
        catch (RuntimeException e) { em.getTransaction().rollback(); }

        finally { em.close(); }

        return null;
    }

}

