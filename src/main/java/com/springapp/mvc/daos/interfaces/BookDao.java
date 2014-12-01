package com.springapp.mvc.daos.interfaces;


import com.springapp.mvc.beans.Book;
import com.springapp.mvc.beans.Student;

/**
 * Created by home-lt on 05/11/14.
 */
public interface BookDao {
    void addBook(Book book);
    void removeBook(Book book);
    Book getBook(int id);

    void update(Book book);
}
