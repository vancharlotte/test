package com.example.clientui.client;

import com.example.clientui.beans.RoleBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "library-role", url = "localhost:9003")
public interface LibraryRoleClient {

    @GetMapping(value = "/role/{id}")
    RoleBean getRole(@PathVariable int id);

}
