package com.springapp.mvc.repositories;


import com.springapp.mvc.beans.Book;
import com.springapp.mvc.beans.Rabbi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);
    Book findByNameAndRabbi_Id(String name, Integer rabbi_id);
    void deleteByRabbi_id(int rabbi_id);
    void deleteByName(String name);

}
