package com.springapp.rabbis.controller;

import com.springapp.rabbis.NamedBean;
import com.springapp.rabbis.beans.Rabbi;
import com.springapp.rabbis.repositories.BookRepository;
import com.springapp.rabbis.repositories.RabbiRepository;
import com.springapp.rabbis.service.interfaces.BookService;
import com.springapp.rabbis.service.interfaces.RabbiService;
import com.springapp.rabbis.toolkit.Toolkit;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

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
    private static Toolkit hebrewToGeorgian = new Toolkit();

    private static Log LOG = new Log4JLogger(RabbiController.class.getName());
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String home() {
        return "defaultTemplate";
    }

    @RequestMapping(value = "/search/{search}", method = RequestMethod.GET)
    public String explicitSearch(@PathVariable("search") String search, ModelMap model) {
        return doSearch(search, model);
    }

    @RequestMapping(value = "/searchBook/{search}", method = RequestMethod.GET)
    public String searchBook(@PathVariable("search") String search, ModelMap model){
        List<Rabbi> rabbis = rabbiService.findByBookContaining(search);
        allRabbisSorted(model, rabbis);
        return "showAll";

    }

    @RequestMapping(value = "/searchRabbi/{search}", method = RequestMethod.GET)
    public String searchRabbi(@PathVariable("search") String search, ModelMap model){
        search = Toolkit.removeBracketsContent(search);
        if (search.startsWith("ה")){
            search = search.substring(1);
        }
        List<Rabbi> rabbis = rabbiRepository.findByNameContainingAndNumIsNotNull(search);
        rabbis.addAll(rabbiRepository.findByNicknameContaining(search));

        allRabbisSorted(model, rabbis);
        return "showAll";

    }
    @RequestMapping(value = "/searchRabbiNickname/{search}", method = RequestMethod.GET)
    public String searchRabbiNickname(@PathVariable("search") String search, ModelMap model){
        search = Toolkit.removeBracketsContent(search);
        List<Rabbi> rabbis = rabbiRepository.findByNicknameContaining(search);

        allRabbisSorted(model, rabbis);
        return "showAll";

    }

    @RequestMapping(value = "/searchBorn/{search}", method = RequestMethod.GET)
    public String searchBorn(@PathVariable("search") String search, ModelMap model){
        search = Toolkit.removeBracketsContent(search);
        List<Rabbi> rabbis = rabbiRepository.findByBorn(search);
        allRabbisSorted(model, rabbis);
        return "showAll";
    }

    @RequestMapping(value = "/searchDied/{search}", method = RequestMethod.GET)
    public String searchDied(@PathVariable("search") String search, ModelMap model){
        search = Toolkit.removeBracketsContent(search);
        List<Rabbi> rabbis = rabbiRepository.findByDied(search);
        allRabbisSorted(model, rabbis);
        return "showAll";
    }

    @RequestMapping(value = "/searchYear/{search}", method = RequestMethod.GET)
    public String searchYear(@PathVariable("search") String search, ModelMap model){
        search = Toolkit.removeBracketsContent(search);
        List<Rabbi> rabbis = rabbiRepository.findByBorn(search);
        rabbis.addAll(rabbiRepository.findByDied(search));
        allRabbisSorted(model, rabbis);
        return "showAll";
    }


    @RequestMapping(value = "/searchDeathLocation/{search}", method = RequestMethod.GET)
    public String searchDeathLocation(@PathVariable("search") String search, ModelMap model){
        search = Toolkit.removeBracketsContent(search);
        List<Rabbi> rabbis = rabbiRepository.findByDeathLocation(search);
        allRabbisSorted(model, rabbis);
        return "showAll";
    }

    @RequestMapping(value = "/searchBirthLocation/{search}", method = RequestMethod.GET)
    public String searchBirthLocation(@PathVariable("search") String search, ModelMap model){
        search = Toolkit.removeBracketsContent(search);
        List<Rabbi> rabbis = rabbiRepository.findByBirthLocation(search);
        allRabbisSorted(model, rabbis);
        return "showAll";
    }

    @RequestMapping(value = "/searchLocation/{search}", method = RequestMethod.GET)
    public String searchLocation(@PathVariable("search") String search, ModelMap model){
        search = Toolkit.removeBracketsContent(search);
        List<Rabbi> rabbis = rabbiRepository.findByBirthLocation(search);
        rabbis.addAll(rabbiRepository.findByDeathLocation(search));

        allRabbisSorted(model, rabbis);
        return "showAll";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(@ModelAttribute("search") String search, ModelMap model){
        return doSearch(search, model);
    }

    private String doSearch(String search, ModelMap model) {
        if (search == null || search.trim().isEmpty()){
            getAll(model);
            LOG.info("search for all");
            return "showAll";
        }
        boolean numeric = StringUtils.isNumeric(search);
        List<Rabbi> rabbis = new ArrayList<Rabbi>();
        LOG.info(search);
        if (numeric) {
            int num = Integer.parseInt(search);
            rabbis.add(rabbiRepository.findByNum(num));
            rabbis.addAll(rabbiRepository.findByDiedGeorgian(search));
            rabbis.addAll(rabbiRepository.findByBornGeorgian(search));
        } else {
            searchAllOptions(search, rabbis);
            if (!search.contains("\"")){
                int length = search.length();
                String other = search.substring(0, length -1).concat("\"").concat(search.substring(length -1, length));
                searchAllOptions(other, rabbis);
            }
            allRabbisSorted(model, rabbis);
        }
        return "showAll";
    }

    @RequestMapping(value = "/rabbi", method = RequestMethod.GET)
    public String rabbi(ModelMap model) {
        model.addAttribute("command", new Rabbi());
        return "rabbi";
    }


//    @RequestMapping(value = "/th", method = RequestMethod.GET)
//    public String th(ModelMap model) {
//        Rabbi rabbi = rabbiService.getRabbi(1);
//        model.addAttribute("rabbi", rabbi);
//        return "thym";
//    }

    @RequestMapping(value = "/update/{num}")
    public String updateRabbi(@PathVariable Integer num, ModelMap model){
        Rabbi rabbi = rabbiRepository.findByNum(num);
        Integer id = rabbi.getId();
        List<Rabbi> students = rabbiService.getStudents(id);
        List<Rabbi> teachers = rabbiService.getTeachers(id);
        replaceWithNumberAndRelation(rabbi, students, teachers);
        rabbi.setStudents(students);
        rabbi.setTeachers(teachers);
        model.addAttribute("command", rabbi);
        model.addAttribute("nbooks", rabbi.getBooks().size());
        model.addAttribute("nstudents", rabbi.getStudents().size());
        model.addAttribute("nteachers", rabbi.getTeachers().size());
        LOG.info("update rabbi: " + rabbi);

        return "rabbi";
    }

    private void replaceWithNumberAndRelation(Rabbi rabbi, List<Rabbi> students, List<Rabbi> teachers) {
        String relation = rabbi.getRelation();
        if (relation != null && !relation.isEmpty()){
            String[] strings = relation.split("#");
            for (int i =0; i< strings.length; i = i+2){
                int num = Integer.parseInt(strings[i]);
                String description = strings[i+1];
                extractRelation(students, num, description);
                extractRelation(teachers, num, description);
            }
        }
    }

    @RequestMapping(value = "/addRabbi", method = RequestMethod.POST)
    public String addRabbi(@ModelAttribute("rabbi")Rabbi rabbi,
                           ModelMap model) {
        model.addAttribute("rabbi", rabbi);
        removeEmptyNameElements(rabbi.getBooks());
        removeEmptyNameElements(rabbi.getStudents());
        extractLinks(rabbi.getStudents(), rabbi);
        removeEmptyNameElements(rabbi.getTeachers());
        extractLinks(rabbi.getTeachers(), rabbi);
        boolean updated = rabbiService.removeIfExist(rabbi);
        updateGeorgian(rabbi);
        rabbiService.addRabbi(rabbi);
        model.addAttribute("updated", updated);
        if (updated){
            LOG.info("updating rabbi: " + rabbi);
        } else {
            LOG.info("adding rabbi: " + rabbi);
        }

        return "showRabbi";
    }



    private void extractLinks(List<Rabbi> rabbis, Rabbi rabbi) {
        ListIterator<Rabbi> iterator = rabbis.listIterator();
        while (iterator.hasNext()){
            Rabbi i = iterator.next();
            String name = i.getName().trim();
            if (StringUtils.isNumeric(name)){
                int num = Integer.parseInt(name);
                Rabbi r = rabbiRepository.findByNum(num);
                iterator.set(r);
            } else if (name.contains("#")){
                int num = Integer.parseInt(name.split("#")[0]);
                Rabbi r = rabbiRepository.findByNum(num);
                iterator.set(r);
                String relation = rabbi.getRelation();
                if (relation == null){
                    rabbi.setRelation(name);
                } else {
                    rabbi.setRelation(relation + name);
                }
            }
        }
    }


    private void updateGeorgian(Rabbi rabbi) {
        String born = rabbi.getBorn();
        if (born != null  && !born.trim().isEmpty()){
            String georgian = hebrewToGeorgian.convertHebrewYearToGeorgian(born);
            rabbi.setBornGeorgian(georgian);
            rabbi.setBorn(hebrewToGeorgian.formatHebrewYear(born));
        }

        String died = rabbi.getDied();
        if (died != null && !died.trim().isEmpty()){
            String georgianDied = hebrewToGeorgian.convertHebrewYearToGeorgian(died);
            rabbi.setDiedGeorgian(georgianDied);
            rabbi.setDied(hebrewToGeorgian.formatHebrewYear(died));
        }
    }



    private void searchAllOptions(String search, List<Rabbi> rabbis) {
        rabbis.addAll(rabbiRepository.findByNameContainingAndNumIsNotNull(search));
        rabbis.addAll(rabbiRepository.findByNicknameContaining(search));
        rabbis.addAll(rabbiRepository.findByBirthLocation(search));
        rabbis.addAll(rabbiRepository.findByDeathLocation(search));
        rabbis.addAll(rabbiRepository.findByBorn(search));
        rabbis.addAll(rabbiRepository.findByDied(search));
        rabbis.addAll(rabbiService.findByBookContaining(search));
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

    @RequestMapping(value = "/rabbi/{num}", method = RequestMethod.GET)
    public String findByNum(@PathVariable Integer num, ModelMap model) {
        Rabbi rabbi = rabbiRepository.findByNum(num);
        List<Rabbi> rabbis = new ArrayList<Rabbi>();
        rabbis.add(rabbi);
        allRabbis(model, rabbis);
        return "showAll";
    }

    @RequestMapping(value="/findRabbi/{id}")
    public String getRabbi(@PathVariable int id, ModelMap model){
        Rabbi rabbi = rabbiService.getRabbi(id);
        setStudentsAndTeachers(rabbi, id);
        model.addAttribute("rabbi", rabbi);
        LOG.info("find rabbi by id: " + id);

        return "showRabbi";
    }

    @RequestMapping(value="/findRabbi/{id}/{graph}")
    public String getRabbiGraph(@PathVariable int id, @PathVariable int graph, ModelMap model){
        Rabbi rabbi = rabbiService.getRabbi(id);
        setStudentsAndTeachers(rabbi, id);
        model.addAttribute("rabbi", rabbi);
        LOG.info("find rabbi by id: " + id);

        return "showRabbiGraph" + graph;
    }

    @RequestMapping(value = "/findByName/{name}")
    public String findByName(@PathVariable String name, ModelMap model){
        Rabbi rabbi = rabbiRepository.findByName(name);
        List<Rabbi> rabbis = new ArrayList<Rabbi>();
        rabbis.add(rabbi);
        allRabbis(model, rabbis);
        return "showAll";
    }

//    @RequestMapping(value = "/removeByName/{name}")
//    public String removeByName(@PathVariable String name, ModelMap model){
//        Rabbi r = rabbiRepository.findByName(name);
//        rabbiService.removeRabbi(r);
//        model.addAttribute("command", new Rabbi());
//        LOG.info("remove rabbi by name: " + name);
//
//        return "rabbi";
//    }

    @RequestMapping(value = "/remove/{num}")
    public String removeByNum(@PathVariable String num, ModelMap model){
        Integer n = Integer.parseInt(num);
        rabbiRepository.deleteByNum(n);
        model.addAttribute("command", new Rabbi());
        LOG.info("remove rabbi by num: " + num);

        return "rabbi";
    }

    @RequestMapping(value = "/allid")
    public String findAll(ModelMap model){
        List<Rabbi> rabbis = rabbiRepository.findAll();

        allRabbisSorted(model, rabbis);
        LOG.info("show all id");

        return "showAll";
    }


    private void allRabbis(ModelMap model, Collection<Rabbi> rabbis) {

        for (Rabbi rabbi: rabbis){
            Integer rabbiId = rabbi.getId();
            setStudentsAndTeachers(rabbi, rabbiId);
        }

        model.addAttribute("rabbis", rabbis);
    }

    private void setStudentsAndTeachers(Rabbi rabbi, Integer rabbiId) {
        List<Rabbi> students = rabbiService.getStudents(rabbiId);
        List<Rabbi> teachers = rabbiService.getTeachers(rabbiId);
        updateRelation(rabbi, students, teachers);
        rabbi.setStudents(students);
        rabbi.setTeachers(teachers);
    }

    private void updateRelation(Rabbi rabbi, List<Rabbi> students, List<Rabbi> teachers) {
        String relation = rabbi.getRelation();
        if (relation != null && !relation.isEmpty()){
            String[] strings = relation.split("#");
            for (int i =0; i< strings.length; i = i+2){
                int num = Integer.parseInt(strings[i]);
                String description = strings[i+1];
                addRelation(students, num, description);
                addRelation(teachers, num, description);
            }
        }
    }

    private void extractRelation(List<Rabbi> rabbis, int num, String description) {
        ListIterator<Rabbi> iterator = rabbis.listIterator();
        while (iterator.hasNext()){
            Rabbi i = iterator.next();
            Integer iNum = i.getNum();
            if (iNum != null){
                if(iNum == num) {
                    i.setName(num + "#" + description);
                } else{
                    i.setName(num +"");
                }
            }
        }
    }

    private void addRelation(List<Rabbi> rabbis, int num, String description) {
        ListIterator<Rabbi> iterator = rabbis.listIterator();
        while (iterator.hasNext()){
            Rabbi i = iterator.next();
            Integer iNum = i.getNum();
            String nickname = i.getNickname();
            if (nickname != null){
                i.setName(i.getName() + ", " +i.getNickname());
            }
            if (iNum != null && iNum == num){
                i.setName(i.getName() + " (" + description + ")");
            }
        }
    }

    private void allRabbisSorted(ModelMap model, List<Rabbi> rabbis) {
        Set<Rabbi> r = new TreeSet<Rabbi>(new Comparator<Rabbi>() {
            @Override
            public int compare(Rabbi o1, Rabbi o2) {
                Integer id1 = o1.getNum();
                Integer id2 = o2.getNum();
                if (id1> id2) return 1;
                if (id1 <id2) return -1;
                return 0;
            }
        });
        r.addAll(rabbis);
        allRabbis(model, r);
    }

    @RequestMapping(value = "/all")
    public String findAllWithNum(ModelMap model){
        getAll(model);
        LOG.info("show all");

        return "showAll";
    }

    @RequestMapping(value = "/timeline")
    public String timeline(ModelMap model){
        getAll(model);
        LOG.info("show all");

        return "timeline";
    }


    private void getAll(ModelMap model) {
        List<Rabbi> rabbis = rabbiRepository.findByNumIsNotNullOrderByNumAsc();
        allRabbis(model, rabbis);
    }


//    @RequestMapping(value = "/removeall")
//    public String removeAll(ModelMap model){
//        rabbiRepository.deleteAll();
//
//        return "showAll";
//    }


}
