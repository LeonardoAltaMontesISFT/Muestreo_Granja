package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.VeterinarianRequest;
import com.blacmircrosystems.Peso_granja.dto.response.VeterinarianResponse;
import com.blacmircrosystems.Peso_granja.entity.Role;
import com.blacmircrosystems.Peso_granja.entity.UserAccount;
import com.blacmircrosystems.Peso_granja.entity.Veterinarian;
import com.blacmircrosystems.Peso_granja.enums.RoleEnum;
import com.blacmircrosystems.Peso_granja.mapper.VeterinarianMapper;
import com.blacmircrosystems.Peso_granja.repository.RoleRepository;
import com.blacmircrosystems.Peso_granja.repository.UserAccountRepository;
import com.blacmircrosystems.Peso_granja.repository.VeterinarianRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VeterinarianService {
    private final VeterinarianRepository repository;
    private final VeterinarianMapper mapper;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    @Transactional
    public VeterinarianResponse create(VeterinarianRequest request){
        if(userAccountRepository.existsByUsername(request.getUserName())){
            throw  new IllegalArgumentException("Numero de trabajador ya esta registrado");
        }
        Role veterinarianRole;
        Optional<Role> exists = roleRepository.findByName(RoleEnum.VETERINARIAN);
            if (exists.isPresent()){
                veterinarianRole= exists.get();
            }else {
                Role role = new Role();
                role.setName(RoleEnum.VETERINARIAN);
                veterinarianRole=roleRepository.save(role);
            }
        UserAccount userAccount= new UserAccount();
            userAccount.setUsername(request.getUserName());
            userAccount.setEnabled(true);
            userAccount.setRole(veterinarianRole);
            userAccount.setPasswordHash(encoder.encode(request.getPassword()));
            UserAccount save = userAccountRepository.save(userAccount);
        Veterinarian veterinarian = mapper.toEntity(request);
        veterinarian.setUserAccount(save);
        Veterinarian saved = repository.save(veterinarian);
        return mapper.toResponse(saved);
    }
    public List<VeterinarianResponse> getAll(){
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }
    @Transactional
    public void delete(Long id){
        Veterinarian veterinarian = finByIdVeterinarian(id);
        repository.delete(veterinarian);

        userAccountRepository.delete(veterinarian.getUserAccount());

    }
    private Veterinarian finByIdVeterinarian(Long id){
        Veterinarian veterinarian = repository.findById(id).orElseThrow(()-> new RuntimeException("Veterinario no encontrado"));
        return veterinarian;
    }
    //Metodo put por especificar necesitamos definir como en administrador puros datos de contacto
}
