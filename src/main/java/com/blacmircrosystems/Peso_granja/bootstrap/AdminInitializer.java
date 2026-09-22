package com.blacmircrosystems.Peso_granja.bootstrap;

import com.blacmircrosystems.Peso_granja.entity.Role;
import com.blacmircrosystems.Peso_granja.entity.UserAccount;
import com.blacmircrosystems.Peso_granja.enums.RoleEnum;
import com.blacmircrosystems.Peso_granja.repository.RoleRepository;
import com.blacmircrosystems.Peso_granja.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.blacmircrosystems.Peso_granja.enums.RoleEnum.ADMIN;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${app.bootstrap.admin.enabled:false}")
    private boolean bootstrapEnabled ;
    @Value("${app.bootstrap.admin.username:}")
    private String initialUsername;
    @Value("${app.bootstrap.admin.password:}")
    private String initialPassword;

    @Override
    public void run(String... args) throws Exception {
        if (!bootstrapEnabled) {
            return;
        }
        if (initialUsername.isBlank() || initialPassword.isBlank()){
            throw  new IllegalStateException(    "Debes configurar el usuario y la contraseña del administrador inicial"
            );
        } else if (userAccountRepository.existsByUsername(initialUsername)) {
return;
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
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(initialUsername);
        userAccount.setPasswordHash(passwordEncoder.encode(initialPassword));
        userAccount.setEnabled(true);
        userAccount.setRole(adminRole);
        userAccountRepository.save(userAccount);

    }

}
