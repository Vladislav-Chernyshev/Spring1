package ru.chernyshev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chernyshev.model.Role;
import ru.chernyshev.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
