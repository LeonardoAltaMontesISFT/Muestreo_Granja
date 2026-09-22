package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.AdministratorRequest;
import com.blacmircrosystems.Peso_granja.dto.response.AdministratorResponse;
import com.blacmircrosystems.Peso_granja.entity.Administrator;
import com.blacmircrosystems.Peso_granja.entity.Role;
import com.blacmircrosystems.Peso_granja.entity.UserAccount;
import com.blacmircrosystems.Peso_granja.enums.RoleEnum;
import com.blacmircrosystems.Peso_granja.mapper.AdministratorMapper;
import com.blacmircrosystems.Peso_granja.repository.AdministratorRepository;
import com.blacmircrosystems.Peso_granja.repository.RoleRepository;
import com.blacmircrosystems.Peso_granja.repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.blacmircrosystems.Peso_granja.enums.RoleEnum.ADMIN;

@Service
@RequiredArgsConstructor
public class AdministratorService {
private final AdministratorRepository administratorRepository;
private final UserAccountRepository userAccountRepository;
private final RoleRepository roleRepository;
private final AdministratorMapper mapper;
private final PasswordEncoder passwordEncoder;

    @Transactional
    public AdministratorResponse create (AdministratorRequest request){
        if(userAccountRepository.existsByUsername(request.getUserName())){
            throw new IllegalArgumentException("Numero de trabajor ya esta registrado");
        }
        Role adminRole;
        Optional<Role> exists = roleRepository.findByName(ADMIN);
        if (exists.isPresent()){
            adminRole= exists.get();
        }else {
            Role rolenew = new Role();
            rolenew.setName(ADMIN);
            adminRole =roleRepository.save(rolenew);

        }
        UserAccount  account= new UserAccount();
        account.setUsername(request.getUserName());
        account.setRole(adminRole);
        account.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        account.setEnabled(true);
        UserAccount userAccount= userAccountRepository.save(account);
        Administrator administrator = mapper.toEntity(request);
        administrator.setUserAccount(userAccount);
        Administrator administrator1 = administratorRepository.save(administrator);
        return mapper.toResponse(administrator1);
    }
}
