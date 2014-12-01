package com.springapp.rabbis.repositories;


import com.springapp.rabbis.beans.Rabbi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RabbiRepository extends JpaRepository<Rabbi, Long> {
    Rabbi findByName(String name);
    Rabbi findByNum(int num);

    void deleteByName(String name);

    void deleteByNum(Integer num);
}
