package com.example.libraryloan.controller;

import com.example.libraryloan.model.Loan;
import com.example.libraryloan.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.time.LocalDate;

import java.sql.Date;
import java.util.List;


@RestController
public class LoanRestController {

    Logger logger = LoggerFactory.getLogger(LoanRestController.class);

    @Autowired
    LoanService loanService;

    @GetMapping(value="/loan/{id}")
    public Loan selectLoan(@PathVariable int id) {
        return loanService.findById(id);
    }

    //addLoan
    @PostMapping(value = "/loan")
        public ResponseEntity<Void> addLoan(@RequestBody Loan loan) {
        Loan loanAdded =   loanService.saveOrUpdate(loan);
        if (loanAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(loanAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    //renewLoan
    @PutMapping(value = "/loan/renew")
    public Loan renewLoan(@RequestBody Loan loan){
        loan.setRenewed(true);
        logger.info(Boolean.toString(loan.isRenewed()));
        return loanService.saveOrUpdate(loan);
    }


    //listLoanNotReturned
    @GetMapping(value ="/loanNotReturnedOnTime")
    public List<Loan> listLoanNotReturnedOnTime(){
        LocalDate localDate = LocalDate.now();
        logger.info(Date.valueOf(localDate).toString());
        return loanService.findByEndDateLessThanAndReturnedFalse(Date.valueOf(localDate));
    }

    @GetMapping(value ="/loans/{user}")
    public List<Loan> listLoans(@PathVariable int user){
        return loanService.findByUser(user);
    }

    @GetMapping(value ="/books/{copy}")
    public boolean copyAvailable(@PathVariable int copy){
        return loanService.copyAvailable(copy);
    }


}

