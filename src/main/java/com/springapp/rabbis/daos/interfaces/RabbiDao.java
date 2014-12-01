package com.springapp.rabbis.daos.interfaces;


import com.springapp.rabbis.beans.Rabbi;

import java.util.List;

/**
 * Created by home-lt on 05/11/14.
 */
public interface RabbiDao {
    void addRabbi(Rabbi rabbi);
    void removeRabbi(Rabbi rabbi);
    Rabbi getRabbi(int id);
    Rabbi getRabbi(String name);
    void updateRabbi(Rabbi rabbi);
    List<Rabbi> getStudents(int id);
    List<Rabbi> getTeachers(int id);
    Rabbi getRabbiByNum(int num);

    List<Rabbi> getStudentsByNum(int num);

    List<Rabbi> getTeachersByNum(int num);

    List<Rabbi> getRabbiByName(String name);
}
