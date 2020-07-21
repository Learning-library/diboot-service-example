package cn.org.jetbrains;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"cn.org.jetbrains.mapper"})
@SpringBootApplication
public class DibootServiceExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DibootServiceExampleApplication.class, args);
    }

}
