package com.blacmircrosystems.Peso_granja.security;

import com.blacmircrosystems.Peso_granja.entity.UserAccount;
import com.blacmircrosystems.Peso_granja.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount;
        Optional<UserAccount> account = userAccountRepository.findByUsername(username);
        if (account.isPresent()){
            userAccount= account.get();
        }else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return User.withUsername(userAccount.getUsername())
                .password(userAccount.getPasswordHash())
                .roles(userAccount.getRole().getName().name())
                .disabled(!userAccount.isEnabled())
                .build();
    }
}
