package com.example.clientui.client;

import com.example.clientui.beans.LoanBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "library-loan", url = "localhost:9002")
public interface LibraryLoanClient {

    @PostMapping(value = "/loan")
    ResponseEntity<Void> addLoan(@RequestBody LoanBean loan);

    @PutMapping(value="/loan")
    void endLoan(@RequestBody LoanBean loan);

    @GetMapping(value ="/loanNotReturnedOnTime")
    List<LoanBean> listLoanNotReturnedOnTime();
}

