package com.blacmircrosystems.Peso_granja.repository;

import com.blacmircrosystems.Peso_granja.entity.PoultryHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PoultryHouseRepository extends JpaRepository<PoultryHouse,Long> {
    Optional<PoultryHouse> findByNumberPoultry(String numberPoultry);

    boolean existsByNumberPoultry(String numberPoultry);
     List<PoultryHouse> findByFarmId(Long farmId);
     boolean existsByFarmIdAndNumberPoultry(Long idFarm, String numberPoultry);

}
