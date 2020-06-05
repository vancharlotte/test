package com.example.libraryloan.service;

import com.example.libraryloan.dao.LoanDao;
import com.example.libraryloan.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanDao loanDao;

    public Loan saveOrUpdate(Loan loan){
        return loanDao.save(loan);
    }

    public List<Loan> findByEndDateLessThanAndReturnedFalse(Date date){
        return loanDao.findByEndDateLessThanAndReturnedFalse(date);
    }

    public Loan findById(int id){
        return  loanDao.findById(id);
    }

    public List<Loan> findByUser(int user){
        return loanDao.findByUser(user);
    }


}
