package ru.yaromich.pets.market.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yaromich.pets.market.auth.entities.Role;
import ru.yaromich.pets.market.auth.entities.User;
import ru.yaromich.pets.market.auth.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
