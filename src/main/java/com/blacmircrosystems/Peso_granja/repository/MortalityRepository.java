package com.blacmircrosystems.Peso_granja.repository;

import com.blacmircrosystems.Peso_granja.entity.Mortality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MortalityRepository  extends JpaRepository<Mortality,Long> {
    @Query("""
        SELECT
            COALESCE(SUM(m.maleDeaths), 0) AS maleDeaths,
            COALESCE(SUM(m.femaleDeaths), 0) AS femaleDeaths
        FROM Mortality m
        WHERE m.flockHouse.id = :flockHouseId
    """)
    MortalityTotal getTotalsByFlockHouseId(
            @Param("flockHouseId") Long flockHouseId
    );
    //Metodo para unificar flockHouse y Mortality
    List<Mortality> findByFlockHouseId(Long id);
}
