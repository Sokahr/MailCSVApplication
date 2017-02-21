package eu.benonline.projecta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by Benjamin Peter.
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BatchCSVEmailApplication {

    public static void main(String[] args) throws Exception {
        System.exit(SpringApplication.exit(SpringApplication.run(BatchCSVEmailApplication.class, args)));
    }
}
