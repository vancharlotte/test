package com.example.librarybatch.config;

import com.example.librarybatch.model.LoanBean;
import com.example.librarybatch.proxy.LoanProxy;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.net.MalformedURLException;
import java.net.URL;
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


    @Bean
    public Job mailJob() throws MalformedURLException {
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
    public JsonItemReader<LoanBean> loanExpiredItemReader() throws MalformedURLException {
        URL resource = new URL("http://localhost:9002/batch/loanNotReturnedOnTime");

        System.out.println("item reader");
        return new JsonItemReaderBuilder<LoanBean>()
                .name("loanExpiredItemReader")
                .resource(new UrlResource(resource))
                .jsonObjectReader(new JacksonJsonObjectReader<>(LoanBean.class))
                .build();
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
