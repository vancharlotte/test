package com.example.librarybatch.config;

import com.example.librarybatch.model.LoanBean;
import com.example.librarybatch.proxy.LoanProxy;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@EnableScheduling

public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemProcessor<LoanBean, LoanBean> loanBeanItemProcessor;

    @Autowired
    private ItemWriter<LoanBean> loanBeanItemWriter;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    LoanProxy loanProxy;


   @Bean
    public Job mailJob() throws IOException {
        System.out.println("mail job");
        Step step1 = stepBuilderFactory.get("step-send-email")
                .<LoanBean, LoanBean>chunk(100)
                .reader(loanExpiredItemReader())
                .processor(loanBeanItemProcessor)
                .writer(loanBeanItemWriter)
                .build();
        return jobBuilderFactory.get("job-send-email")
                .start(step1).build();

    }

    @Bean
    public ListItemReader<LoanBean> loanExpiredItemReader() throws IOException {
        System.out.println("item reader");
        return new ListItemReader<>(loanProxy.listLoanNotReturnedOnTime());
    }

    // job lauch every day at 01:00
    @Scheduled(cron = "0 0 1 * * ?")
    public void launchJob() throws Exception {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(params);
        jobLauncher.run(mailJob(), jobParameters);
    }


}
