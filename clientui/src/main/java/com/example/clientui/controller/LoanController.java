package com.example.clientui.controller;

import com.example.clientui.beans.BookBean;
import com.example.clientui.beans.CopyBean;
import com.example.clientui.beans.LoanBean;
import com.example.clientui.client.LibraryBookClient;
import com.example.clientui.client.LibraryLoanClient;
import com.example.clientui.client.LibraryUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class LoanController {

    @Autowired
    private LibraryLoanClient loanClient;

    @Autowired
    private LibraryBookClient bookClient;


    @RequestMapping("/loans/{user}")
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
}
