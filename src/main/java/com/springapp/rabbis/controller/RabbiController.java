package com.springapp.rabbis.controller;

import com.springapp.rabbis.NamedBean;
import com.springapp.rabbis.beans.Rabbi;
import com.springapp.rabbis.repositories.BookRepository;
import com.springapp.rabbis.repositories.RabbiRepository;
import com.springapp.rabbis.service.interfaces.BookService;
import com.springapp.rabbis.service.interfaces.RabbiService;
import com.springapp.rabbis.toolkit.HebrewToGeorgian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Iterator;
import java.util.List;

@Controller
public class RabbiController {
    @Autowired(required=true)
    RabbiService rabbiService;
    @Autowired(required=true)
    BookService bookService;
    @Autowired(required=true)
    BookRepository bookRepository;
    @Autowired(required = true)
    RabbiRepository rabbiRepository;
    private static HebrewToGeorgian hebrewToGeorgian = new HebrewToGeorgian();

    @RequestMapping(value = "/rabbi", method = RequestMethod.GET)
    public String rabbi(ModelMap model) {
        model.addAttribute("command", new Rabbi());
        return "rabbi";
    }

    @RequestMapping(value = "/update/{num}")
    public String updateRabbi(@PathVariable String num, ModelMap model){
        int n = Integer.parseInt(num);
        Rabbi rabbi = rabbiRepository.findByNum(n);
        List<Rabbi> students = rabbiService.getStudents(rabbi.getId());
        List<Rabbi> teachers = rabbiService.getTeachers(rabbi.getId());
        rabbi.setStudents(students);
        rabbi.setTeachers(teachers);
        model.addAttribute("command", rabbi);
        model.addAttribute("nbooks", rabbi.getBooks().size());
        model.addAttribute("nstudents", rabbi.getStudents().size());
        model.addAttribute("nteachers", rabbi.getTeachers().size());
        return "rabbi";
    }

    @RequestMapping(value = "/addRabbi", method = RequestMethod.POST)
    public String addRabbi(@ModelAttribute("rabbi")Rabbi rabbi,
                             ModelMap model) {
        model.addAttribute("rabbi", rabbi);
        removeEmptyNameElements(rabbi.getBooks());
        removeEmptyNameElements(rabbi.getStudents());
        removeEmptyNameElements(rabbi.getTeachers());
        Rabbi r = rabbiRepository.findByName(rabbi.getName());
        boolean updated = r != null;

        updateGeorgian(rabbi);
        if (updated){
            rabbiService.updateRabbi(rabbi, r.getId());
        } else {
            rabbiService.addRabbi(rabbi);
        }
        model.addAttribute("updated", updated);
        return "showRabbi";
    }

    private void updateGeorgian(Rabbi rabbi) {
        String born = rabbi.getBorn();
        if (born != null  && !born.trim().isEmpty()){
            if (rabbi.getBornGeorgian() == null || rabbi.getBornGeorgian().isEmpty()){
                String georgian = hebrewToGeorgian.convertHebrewYearToGeorgian(born);
                rabbi.setBornGeorgian(georgian);
                rabbi.setBorn(hebrewToGeorgian.formatHebrewYear(born));
            }
        }

        String died = rabbi.getDied();
        if (died != null && !died.trim().isEmpty()){
            if (rabbi.getDiedGeorgian() == null || rabbi.getDiedGeorgian().isEmpty()){
                String georgian = hebrewToGeorgian.convertHebrewYearToGeorgian(died);
                rabbi.setDiedGeorgian(georgian);
                rabbi.setDied(hebrewToGeorgian.formatHebrewYear(died));
            }
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@ModelAttribute("search") String search, ModelMap model){
        model.addAttribute("rabbi", null);
        return "showRabbi";
    }

    private void removeEmptyNameElements(List<? extends NamedBean> list){
        Iterator<? extends NamedBean> iterator = list.iterator();
        while (iterator.hasNext()){
            NamedBean bean = iterator.next();
            if (bean.getName().trim().isEmpty()){
                iterator.remove();
            }
        }
    }

    @RequestMapping(value="/findRabbi/{id}")
    public String getRabbi(@PathVariable String id, ModelMap model){
        int rabbiId = Integer.parseInt(id);
        Rabbi rabbi = rabbiService.getRabbi(rabbiId);
        List<Rabbi> students = rabbiService.getStudents(rabbiId);
        List<Rabbi> teachers = rabbiService.getTeachers(rabbiId);
        rabbi.setStudents(students);
        rabbi.setTeachers(teachers);
        model.addAttribute("rabbi", rabbi);

        return "showRabbi";
    }

    @RequestMapping(value = "/findByName/{name}")
    public String findByName(@PathVariable String name, ModelMap model){
        Rabbi rabbi = rabbiRepository.findByName(name);

        Integer rabbiId = rabbi.getId();
        List<Rabbi> students = rabbiService.getStudents(rabbiId);
        List<Rabbi> teachers = rabbiService.getTeachers(rabbiId);
        rabbi.setStudents(students);
        rabbi.setTeachers(teachers);
        model.addAttribute("rabbi", rabbi);
        return "showRabbi";
    }

    @RequestMapping(value = "/removeByName/{name}")
    public String removeByName(@PathVariable String name, ModelMap model){
        Rabbi r = rabbiRepository.findByName(name);
        rabbiService.removeRabbi(r);
        model.addAttribute("command", new Rabbi());
        return "rabbi";
    }

    @RequestMapping(value = "/remove/{num}")
    public String removeByNum(@PathVariable String num, ModelMap model){
        Integer n = Integer.parseInt(num);
        rabbiRepository.deleteByNum(n);
        model.addAttribute("command", new Rabbi());
        return "rabbi";
    }

    @RequestMapping(value = "/all")
    public String findAll(ModelMap model){
        List<Rabbi> rabbis = rabbiRepository.findAll();

        for (Rabbi rabbi: rabbis){
            Integer rabbiId = rabbi.getId();
            List<Rabbi> students = rabbiService.getStudents(rabbiId);
            List<Rabbi> teachers = rabbiService.getTeachers(rabbiId);
            rabbi.setStudents(students);
            rabbi.setTeachers(teachers);
        }

        model.addAttribute("rabbis", rabbis);
        return "showAll";
    }

    @RequestMapping(value = "/allNum")
    public String findAllWithNum(ModelMap model){
        List<Rabbi> rabbis = rabbiRepository.findByNumIsNotNullOrderByNumAsc();

        for (Rabbi rabbi: rabbis){
            Integer rabbiId = rabbi.getId();
            List<Rabbi> students = rabbiService.getStudents(rabbiId);
            List<Rabbi> teachers = rabbiService.getTeachers(rabbiId);
            rabbi.setStudents(students);
            rabbi.setTeachers(teachers);
        }

        model.addAttribute("rabbis", rabbis);
        return "showAll";
    }



    @RequestMapping(value = "/removeall")
    public String removeAll(ModelMap model){
        rabbiRepository.deleteAll();

        return "showAll";
    }


}
