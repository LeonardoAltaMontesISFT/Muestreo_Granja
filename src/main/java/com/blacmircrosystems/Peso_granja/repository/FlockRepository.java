package com.blacmircrosystems.Peso_granja.repository;

import com.blacmircrosystems.Peso_granja.entity.Flock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface FlockRepository extends JpaRepository<Flock,Long> {
    List<Flock> findAllByOrderByCreatedDateDesc();

}
