package com.blacmircrosystems.Peso_granja.repository;

import com.blacmircrosystems.Peso_granja.entity.Role;
import com.blacmircrosystems.Peso_granja.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleEnum name);
}
