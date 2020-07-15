package com.example.libraryloan.controller;

import com.example.libraryloan.exception.LoanNotFoundException;
import com.example.libraryloan.model.Loan;
import com.example.libraryloan.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;


@RestController
public class LoanRestController {

    Logger logger = LoggerFactory.getLogger(LoanRestController.class);

    @Autowired
    LoanService loanService;

    @GetMapping(value="/loan/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Loan selectLoan(@PathVariable int id) {
        Loan loan = loanService.findById(id);
        if(loan==null) throw new LoanNotFoundException("loan not found");
        return loanService.findById(id);
    }

    //addLoan
    @PostMapping(value = "/loan")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> addLoan(@RequestBody Loan loan) {

        if (loan == null){
            return ResponseEntity.noContent().build();}

        else {
            LocalDate now = LocalDate.now(ZoneId.of("Europe/Paris"));
            LocalDateTime nowMidnight = LocalDateTime.of(now, LocalTime.MIDNIGHT);
            Timestamp timestamp = Timestamp.valueOf(nowMidnight);
            logger.info(timestamp.toString());

            loan.setStartDate(timestamp);
            loan.setEndDate(Timestamp.valueOf(nowMidnight.plusDays(14)));
            loan.setReturned(false);
            loan.setRenewed(false);
            loanService.saveOrUpdate(loan);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(loan.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
    }


    //renewLoan
    @PutMapping(value = "/loan/renew")
    @PreAuthorize("hasAuthority('USER')")
    public Loan renewLoan(@RequestBody Loan loan){
        return loanService.renew(loan);
    }


    //returnLoan
    @PutMapping(value = "/loan/return")
    @PreAuthorize("hasAuthority('USER')")
    public Loan returnLoan(@RequestBody Loan loan){
        return loanService.returnLoan(loan);
    }


    //listLoanNotReturned
    @GetMapping(value ="/batch/loanNotReturnedOnTime")
    public List<Loan> listLoanNotReturnedOnTime(){
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Paris"));
        LocalDateTime todayMidnight = LocalDateTime.of(today, LocalTime.MIDNIGHT);
        Timestamp timestamp = Timestamp.valueOf(todayMidnight);
        logger.info(timestamp.toString());

        return loanService.findByEndDateLessThanAndReturnedFalse(timestamp);
    }

    @GetMapping(value ="/loans/{user}")
    @PreAuthorize("hasAuthority('USER')")
    public List<Loan> listLoans(@PathVariable int user){
        return loanService.findByUser(user);
    }

    @GetMapping(value ="/books/{copy}")
    @PreAuthorize("hasAuthority('USER')")
    public boolean copyAvailable(@PathVariable int copy){
        return loanService.copyAvailable(copy);
    }


}

