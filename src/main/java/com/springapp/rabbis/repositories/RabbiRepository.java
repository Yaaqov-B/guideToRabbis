package com.springapp.rabbis.repositories;


import com.springapp.rabbis.beans.Rabbi;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@CacheConfig(cacheNames = "rabbis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "hiberante")

@Repository
@Transactional
public interface RabbiRepository extends JpaRepository<Rabbi, Long> {
    @Cacheable(value = "rabbis")
    Rabbi findByName(String name);
    @Cacheable(value = "rabbis")
    Rabbi findByNum(int num);
    @Cacheable
    List<Rabbi> findByNumIsNotNullOrderByNumAsc();
    @Cacheable
    List<Rabbi> findByNameContainingAndNumIsNotNull(String name);
    @Cacheable
    List<Rabbi> findByNicknameContaining(String name);
    @Cacheable
    List<Rabbi> findByBirthLocation(String name);
    @Cacheable
    List<Rabbi> findByDeathLocation(String name);
    @Cacheable
    List<Rabbi> findByBorn(String name);
    @Cacheable
    List<Rabbi> findByDied(String name);
    @Cacheable
    List<Rabbi> findByBornGeorgian(String name);
    @Cacheable
    List<Rabbi> findByDiedGeorgian(String name);
    void deleteByName(String name);

    void deleteByNum(Integer num);
}
