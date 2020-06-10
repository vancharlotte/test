package com.example.clientui.client;

import com.example.clientui.beans.CopyBean;
import com.example.clientui.beans.UserBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "library-user")
public interface LibraryUserClient {

    @GetMapping(value = "/library-user/user/{id}")
    UserBean selectUser(@PathVariable int id);

    @GetMapping(value = "/library-user/user/email/{email}")
    UserBean findUserByEmail(@PathVariable String email);

    @PostMapping(value="/library-user/login")
    String login(@RequestParam String email, @RequestParam String password);
}
