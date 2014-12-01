package com.springapp.mvc.daos.impls;

import com.springapp.mvc.beans.Rabbi;
import com.springapp.mvc.daos.interfaces.RabbiDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Repository("rabbiDao")
public class RabbiDaoImpl implements RabbiDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addRabbi(Rabbi rabbi) {
        em.persist(rabbi);
    }

    @Override
    public void removeRabbi(Rabbi rabbi) {
        em.remove(rabbi);
    }

    @Override
    public Rabbi getRabbi(int id) {
        return em.find(Rabbi.class, id);
    }

    @Override
    public Rabbi getRabbi(String name) {
        TypedQuery<Rabbi> query = em.createQuery("select r from rabbi r where r.name= ?1", Rabbi.class);
        query.setParameter(1, name);
        List<Rabbi> resultList = query.getResultList();
        if (resultList.size() > 1) throw new IllegalStateException("Rabbi name should be unique!");
        if (resultList.isEmpty()) return null;
        return resultList.get(0);

    }

    @Override
    public List<Rabbi> getStudents(int id) {
        Query query = em.createQuery("select r.students from rabbi r where r.id= ?1");
        query.setParameter(1, id);
        return query.getResultList();
    }

    @Override
    public List<Rabbi> getTeachers(int id) {
        Query query = em.createQuery("select r.teachers from rabbi r where r.id= ?1");
        query.setParameter(1, id);
        return query.getResultList();
    }

    @Override
    public Rabbi getRabbiByNum(int num) {
        TypedQuery<Rabbi> query = em.createQuery("select r from rabbi r where r.num= ?1", Rabbi.class);
        query.setParameter(1, num);
        List<Rabbi> resultList = query.getResultList();
        if (resultList.size() > 1) throw new IllegalStateException("Rabbi num should be unique!");
        if (resultList.isEmpty()) return null;
        return resultList.get(0);
    }

    @Override
    public List<Rabbi> getStudentsByNum(int num) {
        Query query = em.createQuery("select r.students from rabbi r where r.num= ?1");
        query.setParameter(1, num);
        return query.getResultList();
    }

    @Override
    public List<Rabbi> getTeachersByNum(int num) {
        Query query = em.createQuery("select r.teachers from rabbi r where r.num= ?1");
        query.setParameter(1, num);
        return query.getResultList();
    }

    @Override
    public List<Rabbi> getRabbiByName(String name) {
        TypedQuery<Rabbi> query = em.createQuery("select r from rabbi r where r.name = ?1", Rabbi.class);
        query.setParameter(1, name);
        return query.getResultList();

    }

    @Override
    public void updateRabbi(Rabbi rabbi) {
        em.merge(rabbi);
    }
}
