package ru.yaromich.pets.market.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.yaromich.pets.market.api.RegisterUserDto;
import ru.yaromich.pets.market.auth.entities.Role;
import ru.yaromich.pets.market.auth.entities.User;
import ru.yaromich.pets.market.auth.exceptions.ResourceNotFoundException;
import ru.yaromich.pets.market.auth.repositories.RoleRepository;
import ru.yaromich.pets.market.auth.repositories.UserRepository;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Transactional
    public void registerNewUser(RegisterUserDto registerUserDto) {
        if (registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            String bcryptCachedPassword = passwordEncoder.encode(registerUserDto.getPassword());
            Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new ResourceNotFoundException("Role not found"));
            User user = new User();
            user.setUsername(registerUserDto.getUsername());
            user.setEmail(registerUserDto.getEmail());
            user.setPassword(bcryptCachedPassword);
            user.setRoles(List.of(role));
            userRepository.save(user);
        } else
            throw new IllegalStateException("Password and confirm password are different");
    }
}