package com.xfef0.thymeleaf.services;

import com.xfef0.thymeleaf.domain.Role;
import com.xfef0.thymeleaf.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Optional<Role> getById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        return roleRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}
