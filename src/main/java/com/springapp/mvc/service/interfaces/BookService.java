package com.springapp.mvc.service.interfaces;

import com.springapp.mvc.beans.Book;

/**
 * Created by home-lt on 09/11/14.
 */
public interface BookService {

    void addBook(Book Book);
    void removeBook(Book Book);
    Book getBook(int id);

    void update(Book book);
}
