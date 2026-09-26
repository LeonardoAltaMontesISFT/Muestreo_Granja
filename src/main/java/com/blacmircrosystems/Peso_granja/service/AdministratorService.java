package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.AdministratorContactRequest;
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

import java.util.List;
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
            throw new IllegalArgumentException("Numero de trabajador ya esta registrado");
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
    @Transactional
    public AdministratorResponse update(Long id, AdministratorContactRequest request){
        Administrator administrator = finById(id);
        administrator.setPhone(request.getPhone());
        administrator.setEmail(request.getEmail());
        administrator.setName(request.getName());
        administrator.setLastName(request.getLastName());
        administrator.setAge(request.getAge());
        Administrator save = administratorRepository.save(administrator);
        return  mapper.toResponse(save);

    }
    @Transactional
    public void delete(Long id){
        Administrator administrator = finById(id);
        UserAccount user= findByIdUser(administrator.getUserAccount().getId());
        administratorRepository.delete(administrator);
        userAccountRepository.delete(user);
    }
    public List<AdministratorResponse> getAll(){
        return administratorRepository.findAll().stream().map(mapper::toResponse).toList();
    }
    private Administrator finById(Long id){
        Administrator administrator = administratorRepository.findById(id).orElseThrow(()-> new RuntimeException("Trabajador no encontrado"));
        return administrator;
    }
    private UserAccount findByIdUser(Long id){
        UserAccount user = userAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Cuenta no encontrada"));
        return user;
    }
}
