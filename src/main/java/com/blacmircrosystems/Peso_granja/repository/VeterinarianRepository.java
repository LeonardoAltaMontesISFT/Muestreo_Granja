package com.blacmircrosystems.Peso_granja.repository;

import com.blacmircrosystems.Peso_granja.entity.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VeterinarianRepository extends JpaRepository<Veterinarian,Long> {
    Optional<Veterinarian> findByUserAccountId(Long userId);
}
