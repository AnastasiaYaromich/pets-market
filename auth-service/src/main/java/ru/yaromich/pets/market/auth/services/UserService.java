package ru.yaromich.pets.market.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yaromich.pets.market.auth.entities.Role;
import ru.yaromich.pets.market.auth.entities.User;
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

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByemail(String email) { return userRepository.findByemail(email); }

    @Transactional
    public void registerNewUser(String username, String confirmPassword, String email) {
        User user = new User();
        user.setPassword(confirmPassword);
        user.setUsername(username);
        user.setEmail(email);

        List<Role> roleList = new ArrayList();
        roleList.add(roleService.getUserRole());
        user.setRoles(roleList);

        userRepository.save(user);
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
}