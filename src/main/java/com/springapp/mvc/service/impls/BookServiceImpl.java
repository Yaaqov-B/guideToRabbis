package com.springapp.mvc.service.impls;

import com.springapp.mvc.beans.Book;
import com.springapp.mvc.daos.interfaces.BookDao;
import com.springapp.mvc.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDao bookDao;

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void removeBook(Book book) {
        bookDao.removeBook(book);
    }

    @Override
    public Book getBook(int id) {
        Book book = bookDao.getBook(id);
        return book;
    }
}
