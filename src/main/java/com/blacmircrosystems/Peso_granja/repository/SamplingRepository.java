package com.blacmircrosystems.Peso_granja.repository;

import com.blacmircrosystems.Peso_granja.entity.Sampling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SamplingRepository extends JpaRepository<Sampling,Long
        > {
    Optional<Sampling> findById(Long id);
    List<Sampling> findByPoultryHouseId(Long poultryHouseId);
    List<Sampling> findAllByOrderByCreatedDateDesc();

}
