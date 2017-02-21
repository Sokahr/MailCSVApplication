package eu.benonline.projecta;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by Benjamin Peter.
 */
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchMailConfiguration {
    final JobBuilderFactory jobBuilderFactory;
    final StepBuilderFactory stepBuilderFactory;

    @Value("${corePoolSize}")
    private int corePoolSize=100;
    @Value("${maxPoolSize}")
    private int maxPoolSize=100;
    @Value("${sendMailChunkSize}")
    private int sendMailChunkSize=1;

    @Bean
    @StepScope
    public FlatFileItemReader<EmailRecipient> reader(@Value("#{jobParameters[resource]}") String resource) {
        FlatFileItemReader<EmailRecipient> fileItemReader = new FlatFileItemReader<>();
        fileItemReader.setResource(new FileSystemResource(resource));
        fileItemReader.setLineMapper(new DefaultLineMapper<EmailRecipient>() {{
            setLineTokenizer(new DelimitedLineTokenizer(";") {{
                setNames(new String[]{"email", "firstName", "lastName"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<EmailRecipient>() {{
                setTargetType(EmailRecipient.class);
            }});
        }});
        return fileItemReader;
    }

    @Bean
    public MailServiceItemWriter<EmailRecipient> writer(){
        return new MailServiceItemWriter<>();
    }

    @Bean
    public EmailRecipientItemProcessor processor() {
        return new EmailRecipientItemProcessor();
    }

    @Bean
    public Job sendMailJob() {
        return jobBuilderFactory.get("sendMailJob")
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("SendMailStep-");
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }

    @Bean
    public Step step1() {

        return stepBuilderFactory.get("step1")
                .<EmailRecipient, EmailRecipient>chunk(sendMailChunkSize)
                .reader(reader(null))
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .throttleLimit(10)
                .build();
    }

}
