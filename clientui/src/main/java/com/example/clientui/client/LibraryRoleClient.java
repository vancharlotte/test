package com.example.clientui.client;

import com.example.clientui.beans.RoleBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "library-role")
@RibbonClient(name = "library-role")
public interface LibraryRoleClient {

    @GetMapping(value = "/role/{id}")
    RoleBean getRole(@PathVariable int id);

}
