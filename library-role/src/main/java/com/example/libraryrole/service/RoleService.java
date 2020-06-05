package com.example.libraryrole.service;

import com.example.libraryrole.dao.RoleDao;
import com.example.libraryrole.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role findById(int id){
        return roleDao.findById(id);
    }

}
