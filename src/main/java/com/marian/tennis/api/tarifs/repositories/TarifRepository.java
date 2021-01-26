package com.marian.tennis.api.tarifs.repositories;

import com.marian.tennis.api.tarifs.entity.TarifsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifRepository extends JpaRepository<TarifsEntity, Long> {
    TarifsEntity findByName(String name);

    List<TarifsEntity> findAllByNameIn(List<String> names);

    void deleteByName(String name);

}
