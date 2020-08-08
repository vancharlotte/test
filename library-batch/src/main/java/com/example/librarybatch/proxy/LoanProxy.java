package com.example.librarybatch.proxy;

import com.example.librarybatch.model.LoanBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

//@FeignClient(name = "zuul-server")
@FeignClient(name = "library-loan", url = "http://localhost:9002/")
public interface LoanProxy {


    @GetMapping(value = "/batch/loanNotReturnedOnTime")
    List<LoanBean> listLoanNotReturnedOnTime();


}
