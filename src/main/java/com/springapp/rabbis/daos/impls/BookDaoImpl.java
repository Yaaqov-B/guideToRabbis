package com.springapp.rabbis.daos.impls;

import com.springapp.rabbis.beans.Book;
import com.springapp.rabbis.daos.interfaces.BookDao;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "hiberante")

@Transactional(propagation = Propagation.REQUIRED)
@Repository("bookDao")
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addBook(Book book) {
        em.persist(book);
    }

    @Override
    public void removeBook(Book book) {
        em.remove(book);
    }

    @Override
    public Book getBook(int id) {
        Book book = em.find(Book.class, id);
        return book;
    }

    @Override
    public void update(Book book) {
        em.merge(book);
    }
}
