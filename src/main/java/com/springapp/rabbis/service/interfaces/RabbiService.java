package com.springapp.rabbis.service.interfaces;

import com.springapp.rabbis.beans.Rabbi;

import java.util.Collection;
import java.util.List;

/**
 * Created by home-lt on 09/11/14.
 */
public interface RabbiService {

    void addRabbi(Rabbi rabbi);

    Rabbi getRabbi(int id);
    Rabbi getRabbiByNum(int num);
    Rabbi getRabbi(String name);
    Rabbi getRabbi(Rabbi rabbi);
    List<Rabbi> getStudents(int id);
    List<Rabbi> getTeachers(int id);
    List<Rabbi> getStudentsByNum(int num);
    List<Rabbi> getTeachersByNum(int num);


    Rabbi getRabbiByName(String name);

    void updateRabbi(Rabbi rabbi, Rabbi oldRabbi);

    boolean removeIfExist(Rabbi rabbi);

    List<Rabbi> findByBook(String book);
    List<Rabbi> findByBookContaining(String book);

    List<Rabbi> findByStudentNameContain(String name);


    void updateRabbi(Rabbi rabbi);

    Rabbi addIfNotExist(Rabbi rabbi);

    Rabbi addOrUpdate(Rabbi rabbi);
}
