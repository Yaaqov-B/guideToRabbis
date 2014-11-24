package com.springapp.mvc.controller;

import com.springapp.mvc.beans.Book;
import com.springapp.mvc.beans.Rabbi;
import com.springapp.mvc.beans.Student;
import com.springapp.mvc.service.StudentService;
import com.springapp.mvc.service.interfaces.BookService;
import com.springapp.mvc.service.interfaces.RabbiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RabbiController {
    RabbiService rabbiService;
    BookService bookService;

    @Autowired(required=true)
    public void setRabbiService(RabbiService rabbiService){
        this.rabbiService = rabbiService;
    }
    @Autowired(required=true)
    public void setBookService (BookService bookService) {this.bookService = bookService;}

    @RequestMapping(value = "/rabbi", method = RequestMethod.GET)
    public String rabbi(ModelMap model) {
        model.addAttribute("command", new Rabbi());
        return "rabbi";
    }

    @RequestMapping(value = "/addRabbi", method = RequestMethod.POST)
    public String addRabbi(@ModelAttribute("rabbi")Rabbi rabbi,
                             ModelMap model) {
        model.addAttribute("name", rabbi.getName());
        model.addAttribute("id", rabbi.getId());
        model.addAttribute("books", rabbi.getBooks() );
        model.addAttribute("students", rabbi.getStudents());
        model.addAttribute("teachers", rabbi.getTeachers());
        for (Book book:rabbi.getBooks()){
            book.setRabbi(rabbi);
        }
        for (Rabbi student: rabbi.getStudents()){
            rabbiService.addRabbi(student);
        }
        for (Rabbi teacher: rabbi.getTeachers()){
            rabbiService.addRabbi(teacher);
        }
        rabbiService.addRabbi(rabbi);
        return "showRabbi";
    }

    @RequestMapping(value="/findRabbi/{id}")
    public String getRabbi(@PathVariable String id, ModelMap model){
        int rabbiId = Integer.parseInt(id);
        Rabbi rabbi = rabbiService.getRabbi(rabbiId);
        model.addAttribute("id", rabbi.getId());
        model.addAttribute("name", rabbi.getName());
        model.addAttribute("books", rabbi.getBooks());
        List<Rabbi> students = rabbiService.getStudents(rabbiId);
        model.addAttribute("students", students);
        List<Rabbi> teachers = rabbiService.getTeachers(rabbiId);
        model.addAttribute("teachers", teachers);
        return "showRabbi";
    }
}
