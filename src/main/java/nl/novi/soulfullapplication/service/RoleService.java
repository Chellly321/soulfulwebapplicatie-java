package nl.novi.soulfullapplication.service;

import nl.novi.soulfullapplication.model.Role;
import nl.novi.soulfullapplication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        Role role = roleRepository.findRoleByName(name);
        return role;
    }
}
