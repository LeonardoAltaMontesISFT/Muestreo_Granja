package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.FarmManagerRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FarmManagerResponse;
import com.blacmircrosystems.Peso_granja.entity.*;
import com.blacmircrosystems.Peso_granja.enums.RoleEnum;
import com.blacmircrosystems.Peso_granja.mapper.FarmManagerMapper;
import com.blacmircrosystems.Peso_granja.repository.FarmManagerRepository;
import com.blacmircrosystems.Peso_granja.repository.RoleRepository;
import com.blacmircrosystems.Peso_granja.repository.UserAccountRepository;
import com.blacmircrosystems.Peso_granja.repository.VeterinarianRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FarmManagerService {
    private final FarmManagerRepository repository;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final FarmManagerMapper mapper;
    private final PasswordEncoder encoder;
    private  final VeterinarianRepository veterinarianRepository;
    @Transactional
    public FarmManagerResponse create(FarmManagerRequest request){
            if(userAccountRepository.existsByUsername(request.getUserName())){
                throw new IllegalArgumentException("Numero de trabajador ya registrado");
            }
        Role farmManagerRole;
        Optional<Role> role = roleRepository.findByName(RoleEnum.FARM_MANAGER);
        if (role.isPresent()){
            farmManagerRole = role.get();
        }else {
            Role role1 =  new Role();
            role1.setName(RoleEnum.FARM_MANAGER);
            farmManagerRole= roleRepository.save(role1);
        }
        UserAccount account = new UserAccount();
        account.setUsername(request.getUserName());
        account.setPasswordHash(encoder.encode(request.getPassword()));
        account.setEnabled(true);
        account.setRole(farmManagerRole);
        UserAccount userAccount= userAccountRepository.save(account);
        FarmManager manager= mapper.toEntity(request);
        manager.setUserAccount(userAccount);
        Long veterinarianId = request.getIdVeterinarian();

        if (veterinarianId != null) {
            Veterinarian veterinarian = veterinarianRepository
                    .findById(veterinarianId)
                    .orElseThrow(() ->
                            new RuntimeException("Veterinario no encontrado")
                    );

            manager.setVeterinarian(veterinarian);
        }
        FarmManager farmManager = repository.save(manager);
        return mapper.toResponse(farmManager);

    }
    @Transactional
    public void delete(Long id){

        FarmManager manager= findByIdFarmManager(id);
        repository.delete(manager);
        userAccountRepository.delete(manager.getUserAccount());

    }
    public List<FarmManagerResponse> getAll(){
        List<FarmManager> list = repository.findAll();
        return list.stream().map(mapper::toResponse).toList();
    }

    private FarmManager findByIdFarmManager(Long id){
        FarmManager manager= repository.findById(id).orElseThrow(()-> new RuntimeException("Trabajador no encontrado"));
        return manager;
    }
}
