package com.springapp.rabbis.service.interfaces;

import com.springapp.rabbis.beans.Rabbi;

import java.util.List;

/**
 * Created by home-lt on 09/11/14.
 */
public interface RabbiService {

    void addRabbi(Rabbi rabbi);
    void updateRabbi(Rabbi rabbi, Integer id);
    void removeRabbi(Rabbi rabbi);
    Rabbi getRabbi(int id);
    Rabbi getRabbiByNum(int num);
    Rabbi getRabbi(String name);
    List<Rabbi> getStudents(int id);
    List<Rabbi> getTeachers(int id);
    List<Rabbi> getStudentsByNum(int num);
    List<Rabbi> getTeachersByNum(int num);

    List<Rabbi> getRabbiByName(String name);

    List<Rabbi> addIfNotExistRabbi(Rabbi rabbi);
}
