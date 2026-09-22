package com.blacmircrosystems.Peso_granja.repository;

import com.blacmircrosystems.Peso_granja.entity.Role;
import com.blacmircrosystems.Peso_granja.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
    Optional<UserAccount> findByUsername(String username);
    boolean existsByUsername(String username);
}
