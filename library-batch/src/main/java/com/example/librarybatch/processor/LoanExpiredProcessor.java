package com.example.librarybatch.processor;

import com.example.librarybatch.model.AccountBean;
import com.example.librarybatch.model.LoanBean;
import com.example.librarybatch.proxy.UserProxy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class LoanExpiredProcessor implements ItemProcessor<LoanBean, LoanBean> {

    @Autowired
    UserProxy userProxy;

    @Override
    public LoanBean process(LoanBean loanBean) throws Exception {
        System.out.println("item processor");
        String email = (userProxy.selectAccount(loanBean.getUser())).getEmail();
        loanBean.setUserEmail(email);
        System.out.println(email);
        return loanBean;
    }


}
