package com.springapp.rabbis;

import com.springapp.rabbis.beans.Book;
import com.springapp.rabbis.beans.Rabbi;
import com.springapp.rabbis.service.interfaces.BookService;
import com.springapp.rabbis.service.interfaces.RabbiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class RabbiTests {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    RabbiService rabbiService;
    BookService bookService;

    @Autowired(required=true)
    public void setRabbiService(RabbiService rabbiService){
        this.rabbiService = rabbiService;
    }
    @Autowired(required=true)
    public void setBookService (BookService bookService) {this.bookService = bookService;}

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void simple() throws Exception {
        mockMvc.perform(get("/rabbi"))
                .andExpect(status().isOk())
                .andExpect(view().name("rabbi"));
    }

    @Test
    public void addRabbi() throws  Exception{
        Rabbi rashbi = new Rabbi();
        rabbiService.addRabbi(rashbi);

    }

    @Test
    public void test1() throws Exception {
        Rabbi rashbi = new Rabbi();
        List<Book> zohar = new ArrayList<Book>();
        for (int i=0; i<2 ; i++){
            Book b = new Book();
            b.setName("zohar" + i);
            b.setRabbi(rashbi);
            zohar.add(b);
        }
        rashbi.setBooks(zohar);
        rashbi.setNum(1);
        rashbi.setName("rashbi");
        rabbiService.addRabbi(rashbi);

        Rabbi rambam = new Rabbi();
        List<Book> yad = new ArrayList<Book>();
        for (int i=0; i<3 ; i++){
            Book b = new Book();
            b.setName("yad" + i);
            b.setRabbi(rambam);
            yad.add(b);
        }
        rambam.setBooks(yad);
        rambam.setNum(2);
        rambam.setName("RAmbam");

        Rabbi anotherRabbi = new Rabbi();
        anotherRabbi.setNum(3);
        anotherRabbi.setName("another");
        rabbiService.addRabbi(anotherRabbi);

        Rabbi aristo = new Rabbi();
        aristo.setNum(4);
        aristo.setName("aristo");
        rabbiService.addRabbi(aristo);

        List<Rabbi>students = new ArrayList<Rabbi>();
        List<Rabbi>teachers = new ArrayList<Rabbi>();

        students.add(rashbi);
        students.add(anotherRabbi);

        teachers.add(aristo);
        rambam.setStudents(students);
        rambam.setTeachers(teachers);
        rabbiService.addRabbi(rambam);

        Rabbi isRambam = rabbiService.getRabbiByNum(2);
        System.out.println(isRambam);
        List<Rabbi> rambamStudents = rabbiService.getStudentsByNum(2);
        for (Rabbi r: rambamStudents){
            System.out.println(r);
            Book b = new Book();
            b.setName(r.getName() + "boko");
            b.setRabbi(r);
            r.getBooks().add(b);

            rabbiService.addRabbi(r);

        }
        Rabbi isRashbi = rabbiService.getRabbiByNum(1);
        System.out.println(isRashbi);

        List<Rabbi> rambamTeachers = rabbiService.getTeachersByNum(2);
        for (Rabbi r: rambamTeachers){
            System.out.println(r);
            Book b = new Book();
            b.setName(r.getName() + "boko");
            b.setRabbi(r);
            r.getBooks().add(b);

            rabbiService.addRabbi(r);

        }
        Rabbi isAristo = rabbiService.getRabbiByNum(4);
        System.out.println(isAristo);
//        Thread.sleep(40000);

    }

    @Test
    public void test2() throws Exception {
        Rabbi rashbi = new Rabbi();
        List<Book> zohar = new ArrayList<Book>();
        for (int i=0; i<2 ; i++){
            Book b = new Book();
            b.setName("zohar" + i);
            b.setRabbi(rashbi);
            zohar.add(b);
        }
        rashbi.setBooks(zohar);
        rashbi.setNum(1);
        rashbi.setName("rashbi");
        rabbiService.addRabbi(rashbi);

        Rabbi isRashbi = rabbiService.getRabbi("rashbi");
        System.out.println(isRashbi);
    }

    @Test
    public void TwoRabbiOneStudent() throws Exception {
        Rabbi rashbi = new Rabbi();
        List<Book> zohar = new ArrayList<Book>();
        for (int i=0; i<2 ; i++){
            Book b = new Book();
            b.setName("zohar" + i);
            b.setRabbi(rashbi);
            zohar.add(b);
        }
        rashbi.setBooks(zohar);
        rashbi.setNum(1);
        rashbi.setName("rashbi");

        Rabbi rambam = new Rabbi();
        List<Book> yad = new ArrayList<Book>();
        for (int i=0; i<3 ; i++){
            Book b = new Book();
            b.setName("yad" + i);
            b.setRabbi(rambam);
            yad.add(b);
        }
        rambam.setBooks(yad);
        rambam.setNum(2);
        rambam.setName("RAmbam");

        Rabbi student = new Rabbi();
        student.setNum(3);
        student.setName("student");
        rabbiService.addRabbi(student);


        List<Rabbi>rambamstudents = new ArrayList<Rabbi>();
        List<Rabbi>rashbistudents = new ArrayList<Rabbi>();

        rambamstudents.add(student);
        rashbistudents.add(student);
        rambam.setStudents(rambamstudents);
        rashbi.setStudents(rashbistudents);
        rabbiService.addRabbi(rambam);
        rabbiService.addRabbi(rashbi);

        Rabbi isRambam = rabbiService.getRabbiByNum(2);
        System.out.println(isRambam);

        List<Rabbi> rambamStudents = rabbiService.getStudentsByNum(2);

        for (Rabbi r: rambamStudents){
            System.out.println("rambam student: " + r);
            Book b = new Book();
            b.setName(r.getName() + "boko");
            b.setRabbi(r);
            r.getBooks().add(b);

            rabbiService.addRabbi(r);

        }
        Rabbi isRashbi = rabbiService.getRabbiByNum(1);
        System.out.println(isRashbi);

        List<Rabbi> rashbiStudents = rabbiService.getStudentsByNum(1);

        for (Rabbi r: rashbiStudents){
            System.out.println("rashbi studnet: " +r);
            Book b = new Book();
            b.setName(r.getName() + "boko");
            b.setRabbi(r);
            r.getBooks().add(b);

            rabbiService.addRabbi(r);

        }
//        Thread.sleep(40000);

    }
}
