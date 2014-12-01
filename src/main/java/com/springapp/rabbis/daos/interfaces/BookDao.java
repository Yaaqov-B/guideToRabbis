package com.springapp.rabbis.daos.interfaces;


import com.springapp.rabbis.beans.Book;

/**
 * Created by home-lt on 05/11/14.
 */
public interface BookDao {
    void addBook(Book book);
    void removeBook(Book book);
    Book getBook(int id);

    void update(Book book);
}
