package com.marian.tennis.api.tarifs.repositories;

import com.marian.tennis.api.tarifs.entity.TarifsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifRepository extends JpaRepository<TarifsEntity,Long> {
    TarifsEntity findByName(String name);
}
