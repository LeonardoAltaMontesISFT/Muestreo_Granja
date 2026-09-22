package com.blacmircrosystems.Peso_granja.repository;

import com.blacmircrosystems.Peso_granja.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,Long> {
Optional<Administrator> findByUserAccountId(Long userAccountId);
}

