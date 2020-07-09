package com.example.librarybatch.proxy;

import com.example.librarybatch.model.AccountBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-server", url = "http://localhost:9191/")
public interface UserProxy {

        @GetMapping(value = "/batch/account/{id}")
    AccountBean selectAccount(@PathVariable int id);
}
