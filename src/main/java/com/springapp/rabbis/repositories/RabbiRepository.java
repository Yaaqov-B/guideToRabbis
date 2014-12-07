package com.springapp.rabbis.repositories;


import com.springapp.rabbis.beans.Rabbi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RabbiRepository extends JpaRepository<Rabbi, Long> {
    Rabbi findByName(String name);
    Rabbi findByNum(int num);
    List<Rabbi> findByNumIsNotNullOrderByNumAsc();
    List<Rabbi> findByNameContaining(String name);
    List<Rabbi> findByNicknameContaining(String name);
    List<Rabbi> findByBirthLocation(String name);
    List<Rabbi> findByDeathLocation(String name);
    List<Rabbi> findByBorn(String name);
    List<Rabbi> findByDied(String name);
    List<Rabbi> findByBornGeorgian(String name);
    List<Rabbi> findByDiedGeorgian(String name);
    void deleteByName(String name);

    void deleteByNum(Integer num);
}
