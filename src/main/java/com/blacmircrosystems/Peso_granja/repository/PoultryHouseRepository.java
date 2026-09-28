package com.blacmircrosystems.Peso_granja.repository;

import com.blacmircrosystems.Peso_granja.entity.PoultryHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PoultryHouseRepository extends JpaRepository<PoultryHouse,Long> {
    Optional<PoultryHouse> findByNumberPoultry(String numberPoultry);

    boolean existsByNumberPoultry(String numberPoultry);
     List<PoultryHouse> findByFarmId(Long farmId);
     boolean existsByFarmIdAndNumberPoultry(Long idFarm, String numberPoultry);
    @Query("""
    SELECT ph
    FROM PoultryHouse ph
    WHERE ph.farm.id = :farmId
    AND NOT EXISTS (
        SELECT fh.id
        FROM FlockHouse fh
        WHERE fh.poultryHouse.id = ph.id
        AND fh.flock.id = :flockId
    )
""")
    List<PoultryHouse> findAvailableForFlock(
            @Param("farmId") Long farmId,
            @Param("flockId") Long flockId
    );
}
