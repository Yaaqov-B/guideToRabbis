package com.springapp.mvc.service.impls;

import com.springapp.mvc.beans.Rabbi;
import com.springapp.mvc.daos.interfaces.RabbiDao;
import com.springapp.mvc.service.interfaces.RabbiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RabbiServiceImpl implements RabbiService{


    @Autowired
    private RabbiDao rabbiDao;

    @Override
    public void addRabbi(Rabbi rabbi) {
        rabbiDao.addRabbi(rabbi);
    }

    @Override
    public void updateRabbi(Rabbi rabbi) {
        rabbiDao.updateRabbi(rabbi);
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
}
