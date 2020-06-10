package com.example.clientui.client;

import com.example.clientui.beans.LoanBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "library-loan")
public interface LibraryLoanClient {

    @GetMapping(value="library-loan/loan/{id}")
    LoanBean selectLoan(@PathVariable int id);

    @PostMapping(value = "library-loan/loan")
    ResponseEntity<Void> addLoan(@RequestBody LoanBean loan);

    @PutMapping(value = "library-loan/loan/renew")
    LoanBean renewLoan(@RequestBody LoanBean loan);

    @GetMapping(value = "library-loan/loanNotReturnedOnTime")
    List<LoanBean> listLoanNotReturnedOnTime();

    @GetMapping(value = "library-loan/loans/{user}")
    List<LoanBean> listLoans(@PathVariable int user);


    @GetMapping(value = "library-loan/books/{copy}")
    boolean copyAvailable(@PathVariable int copy);

}