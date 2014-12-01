package com.springapp.rabbis.controller;

import com.springapp.rabbis.beans.Book;
import com.springapp.rabbis.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
    BookService bookService;

    @Autowired(required=true)
    public void setPersonService(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book")Book Book,
                             ModelMap model) {
        model.addAttribute("name", Book.getName());
        model.addAttribute("id", Book.getId());
        bookService.addBook(Book);

        return "showBook";
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String book(ModelMap model) {
        model.addAttribute("command", new Book());
        return "book";
    }

    @RequestMapping(value="/findBook/{id}")
    public String getBook(@PathVariable String id, ModelMap model){
        Book Book = bookService.getBook(Integer.parseInt(id));
        model.addAttribute("name", Book.getName());
        model.addAttribute("id", Book.getId());
        model.addAttribute("rabbi", Book.getRabbi());
        return "showBook";
    }
}
