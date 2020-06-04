package com.example.clientui.client;

import com.example.clientui.beans.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "library-user", url = "localhost:9004")
public interface LibraryUserClient {

    @GetMapping(value = "/user/{id}")
    UserBean selectUser(@PathVariable int id);

    @GetMapping(value = "/user/email/{email}")
    UserBean findUserByEmail(@PathVariable String email);
}
