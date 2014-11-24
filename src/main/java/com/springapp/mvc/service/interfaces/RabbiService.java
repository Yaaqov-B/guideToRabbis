package com.springapp.mvc.service.interfaces;

import com.springapp.mvc.beans.Rabbi;

import java.util.List;

/**
 * Created by home-lt on 09/11/14.
 */
public interface RabbiService {

    void addRabbi(Rabbi rabbi);
    void updateRabbi(Rabbi rabbi);
    void removeRabbi(Rabbi rabbi);
    Rabbi getRabbi(int id);
    Rabbi getRabbiByNum(int num);
    Rabbi getRabbi(String name);
    List<Rabbi> getStudents(int id);
    List<Rabbi> getTeachers(int id);
    List<Rabbi> getStudentsByNum(int num);
    List<Rabbi> getTeachersByNum(int num);
}
