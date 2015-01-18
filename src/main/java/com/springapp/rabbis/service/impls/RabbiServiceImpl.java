package com.springapp.rabbis.service.impls;

import com.springapp.rabbis.beans.Book;
import com.springapp.rabbis.beans.Rabbi;
import com.springapp.rabbis.daos.interfaces.RabbiDao;
import com.springapp.rabbis.repositories.BookRepository;
import com.springapp.rabbis.repositories.RabbiRepository;
import com.springapp.rabbis.service.interfaces.BookService;
import com.springapp.rabbis.service.interfaces.RabbiService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class RabbiServiceImpl implements RabbiService{

    private static Log LOG = new Log4JLogger(RabbiServiceImpl.class.getName());

    @Autowired
    private RabbiDao rabbiDao;
    @Autowired(required=true)
    BookRepository bookRepository;
    @Autowired(required=true)
    RabbiRepository rabbiRepository;
    @Autowired(required=true)
    BookService bookService;

    @Override
    public void addRabbi(Rabbi rabbi) {
        updateStudentsAndTeachers(rabbi);
        setBooks(rabbi);
        rabbiDao.addRabbi(rabbi);
    }

    private void setBooks(Rabbi rabbi) {
        List<Book> books = rabbi.getBooks();
        if (books == null) return;
        for (Book book: books){
            book.setRabbi(rabbi);
        }
    }

    public void updateRabbi(Rabbi newRabbi, Rabbi oldRabbi) {
        removeBooks(oldRabbi);
        copyRabbi(newRabbi, oldRabbi);
        setBooks(oldRabbi);

        updateStudentsAndTeachers(oldRabbi);
        rabbiDao.updateRabbi(oldRabbi);
    }

    @Override
    public boolean removeIfExist(Rabbi rabbi) {
        Rabbi oldRabbi = rabbiRepository.findByName(rabbi.getName());
        boolean toRemove = oldRabbi != null;
        if (toRemove){
            System.out.println("remove rabbi : " + oldRabbi);
            LOG.info("remove rabbi : " + oldRabbi);
            removeRabbi(oldRabbi);
        }
        return toRemove;
    }

    @Override
    public List<Rabbi> findByBook(String book) {
        return rabbiDao.findByBook(book);
    }

    @Override
    public List<Rabbi> findByBookContaining(String book) {
        return rabbiDao.findByBookContaining(book);
    }

    @Override
    public List<Rabbi> findByStudentNameContain(String name) {
        return rabbiDao.findByStudentNameContain(name);
    }

    @Override
    public void updateRabbi(Rabbi rabbi) {
        rabbiDao.updateRabbi(rabbi);
    }

    private void removeBooks(Rabbi rabbi) {
        bookRepository.deleteByRabbi_id(rabbi.getId());
    }

    private void copyRabbi(Rabbi newRabbi, Rabbi oldRabbi){
        oldRabbi.setNum(newRabbi.getNum());
        oldRabbi.setBirthLocation(newRabbi.getBirthLocation());
        oldRabbi.setBooks(newRabbi.getBooks());
        oldRabbi.setBorn(newRabbi.getBorn());
        oldRabbi.setBornGeorgian(newRabbi.getBornGeorgian());
        oldRabbi.setDeathLocation(newRabbi.getDeathLocation());
        oldRabbi.setDied(newRabbi.getDied());
        oldRabbi.setDiedGeorgian(newRabbi.getDiedGeorgian());
        oldRabbi.setName(newRabbi.getName());
        oldRabbi.setNickname(newRabbi.getNickname());
        oldRabbi.setStudents(newRabbi.getStudents());
        oldRabbi.setTeachers(newRabbi.getTeachers());
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
    public Rabbi getRabbiByName(String name) {
        return rabbiDao.getRabbiByName(name);
    }

    private Rabbi addIfNotExistRabbi(Rabbi rabbi) {
        String name = rabbi.getName();
        Rabbi byName = rabbiRepository.findByName(name);
        if (byName == null){
            rabbiDao.addRabbi(rabbi);
        }
        return byName;
    }

    private void updateStudentsAndTeachers(Rabbi r) {
        updateRabbis(r.getStudents());
        updateRabbis(r.getTeachers());
    }

    private void updateRabbis(List<Rabbi> rabbis) {
        if (rabbis == null) return;
        Iterator<Rabbi> iterator = rabbis.iterator();
        List<Rabbi> toAdd = new ArrayList<Rabbi>();
        while (iterator.hasNext()){
            Rabbi rabbi = iterator.next();
            Rabbi updated = addIfNotExistRabbi(rabbi);
            if (updated != null ){
                iterator.remove();
                toAdd.add(updated);
            }
        }
        rabbis.addAll(toAdd);

    }

}
