package com.example.clientui.controller;

import com.example.clientui.beans.BookBean;
import com.example.clientui.beans.CopyBean;
import com.example.clientui.beans.LoanBean;
import com.example.clientui.client.LibraryBookClient;
import com.example.clientui.client.LibraryLoanClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class LoanController {

    private Logger logger = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private LibraryLoanClient loanClient;

    @Autowired
    private LibraryBookClient bookClient;


    @GetMapping("/loans/{user}")
    public String ListLoans(@PathVariable int user, Model model) {

        List<LoanBean> loans = loanClient.listLoans(user);
        LinkedHashMap<LoanBean, BookBean> map = new LinkedHashMap<>();

        for (int i = 0; i < loans.size(); i++) {
            CopyBean copy= bookClient.selectCopy(loans.get(i).getCopy());
            BookBean book = bookClient.displayBook(copy.getBook());
            map.put(loans.get(i), book);
        }

        model.addAttribute("loans", loans);
        model.addAttribute("map", map);
        return "ListLoans";
    }

    @GetMapping( value="/loan/renew/{id}")
    public String renewLoan(@PathVariable int id){
        LoanBean loan =  loanClient.selectLoan(id);
        loanClient.renewLoan(loan);
        logger.info("put loan");
        return "redirect:/loans/4";

    }
}
