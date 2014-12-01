package com.springapp.rabbis.service.impls;

import com.springapp.rabbis.beans.Book;
import com.springapp.rabbis.beans.Rabbi;
import com.springapp.rabbis.daos.interfaces.RabbiDao;
import com.springapp.rabbis.repositories.BookRepository;
import com.springapp.rabbis.service.interfaces.BookService;
import com.springapp.rabbis.service.interfaces.RabbiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class RabbiServiceImpl implements RabbiService{


    @Autowired
    private RabbiDao rabbiDao;
    @Autowired(required=true)
    BookRepository bookRepository;
    @Autowired(required=true)
    BookService bookService;

    @Override
    public void addRabbi(Rabbi rabbi) {
        updateStudentsAndTeachers(rabbi);
        setBooks(rabbi);
        rabbiDao.addRabbi(rabbi);
    }

    private void setBooks(Rabbi rabbi) {
        for (Book book:rabbi.getBooks()){
            System.out.println("SET " + book);
            book.setRabbi(rabbi);
        }
    }

    @Override
    public void updateRabbi(Rabbi fromRabbi, Integer id) {
        Rabbi toRabbi = getRabbi(id);
        removeBooks(toRabbi);
        copyRabbi(fromRabbi, toRabbi);
        setBooks(toRabbi);

        updateStudentsAndTeachers(toRabbi);
        rabbiDao.updateRabbi(toRabbi);
    }

    private void removeBooks(Rabbi rabbi) {
        bookRepository.deleteByRabbi_id(rabbi.getId());
    }

    private void copyRabbi(Rabbi from, Rabbi to){
        to.setNum(from.getNum());
        to.setBirthLocation(from.getBirthLocation());
        to.setBooks(from.getBooks());
        to.setBorn(from.getBorn());
        to.setBornGeorgian(from.getBornGeorgian());
        to.setDeathLocation(from.getDeathLocation());
        to.setDied(from.getDied());
        to.setDiedGeorgian(from.getDiedGeorgian());
        to.setName(from.getName());
        to.setNickname(from.getNickname());
        to.setStudents(from.getStudents());
        to.setTeachers(from.getTeachers());
    }

    @Override
    public void removeRabbi(Rabbi rabbi) {
        rabbiDao.removeRabbi(rabbi);
    }

    @Override
    public Rabbi getRabbi(int id) {
        Rabbi rabbi = rabbiDao.getRabbi(id);
        return rabbi;
    }

    @Override
    public Rabbi getRabbiByNum(int num) {
        return rabbiDao.getRabbiByNum(num);
    }

    @Override
    public Rabbi getRabbi(String name) {
        return rabbiDao.getRabbi(name);
    }

    @Override
    public List<Rabbi> getStudents(int id) {
        return rabbiDao.getStudents(id);
    }

    @Override
    public List<Rabbi> getTeachers(int id) {
        return rabbiDao.getTeachers(id);
    }

    @Override
    public List<Rabbi> getStudentsByNum(int num) {
        return rabbiDao.getStudentsByNum(num);
    }

    @Override
    public List<Rabbi> getTeachersByNum(int num) {
        return rabbiDao.getTeachersByNum(num);
    }

    @Override
    public List<Rabbi> getRabbiByName(String name) {
        return rabbiDao.getRabbiByName(name);
    }

    @Override
    public List<Rabbi> addIfNotExistRabbi(Rabbi rabbi) {
        String name = rabbi.getName();
        List<Rabbi> byName = getRabbiByName(name);
        if (byName == null || byName.isEmpty()){
            System.out.println("before add: " + rabbi.getBooks());

            rabbiDao.addRabbi(rabbi);
            System.out.println("after add: " + rabbi.getBooks());

        } else {
        }
        return byName;
    }

    private void updateStudentsAndTeachers(Rabbi r) {

        updateRabbis(r.getStudents());
        System.out.println("to4: " + r.getBooks());

        updateRabbis(r.getTeachers());

    }

    private void updateRabbis(List<Rabbi> rabbis) {
        Iterator<Rabbi> iterator = rabbis.iterator();
        List<Rabbi> toAdd = new ArrayList<Rabbi>();
        while (iterator.hasNext()){
            Rabbi rabbi = iterator.next();
            List<Rabbi> updated = addIfNotExistRabbi(rabbi);
            if (updated != null && !updated.isEmpty()){
                iterator.remove();
                toAdd.add(updated.get(0));
            }
        }
        rabbis.addAll(toAdd);

    }

    private void updateBooks(Rabbi rabbi){
        List<Book> books = rabbi.getBooks();
        Iterator<Book> iterator = books.iterator();
        List<Book> toAdd = new ArrayList<Book>();
        System.out.println(rabbi.getBooks());
        while (iterator.hasNext()){
            Book book = iterator.next();
            Book byName = bookRepository.findByNameAndRabbi_Id(book.getName(), rabbi.getId());
            if (byName != null){
                System.out.println("REMOVE : " + book + ", ADDING " + byName);
                iterator.remove();
                toAdd.add(byName);
            } else {
                book.setRabbi(rabbi);
            }
        }
        books.addAll(toAdd);
    }
}
