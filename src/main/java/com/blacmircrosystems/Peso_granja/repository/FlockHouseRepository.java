package com.blacmircrosystems.Peso_granja.repository;

import com.blacmircrosystems.Peso_granja.entity.FlockHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlockHouseRepository extends JpaRepository<FlockHouse,Long> {
boolean existsByFlockIdAndPoultryHouseId(Long flockId, Long poultryhouseId);
    boolean existsByFlockIdAndPoultryHouseIdAndIdNot(
            Long flockId,
            Long poultryHouseId,
            Long flockHouseId
    );
    List<FlockHouse> findByFlockId(Long flocId);
}
