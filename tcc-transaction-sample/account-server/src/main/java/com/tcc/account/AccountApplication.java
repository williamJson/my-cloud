package com.tcc.account;

import com.alibaba.druid.pool.DruidDataSource;
import org.mengyun.tcctransaction.TransactionRepository;
import org.mengyun.tcctransaction.recover.RecoverConfig;
import org.mengyun.tcctransaction.spring.recover.DefaultRecoverConfig;
import org.mengyun.tcctransaction.spring.repository.SpringJdbcTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/11/3
 */
@SpringBootApplication
@ImportResource("classpath:tcc-transaction.xml")
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    /**
     * tcc-transaction专用数据库链接
     *
     * @return
     */
    @Bean(name = "tccDataSource", destroyMethod = "close")
    public DruidDataSource tccDataSource() {
        DruidDataSource tccDataSource = new DruidDataSource();
        tccDataSource.setUrl("jdbc:mysql://47.110.49.234:3306/tcc_rep?useUnicode=true&useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        tccDataSource.setUsername("root");
        tccDataSource.setPassword("wyq1234");
        tccDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return tccDataSource;
    }


    /**
     * 持久化tcc事物日志
     *
     * @param dataSource
     * @return
     */
    @Bean
    public TransactionRepository transactionRepository(@Autowired @Qualifier("tccDataSource") DataSource dataSource) {
        SpringJdbcTransactionRepository repository = new SpringJdbcTransactionRepository();
        repository.setDataSource(dataSource);
        repository.setDomain("ACCOUNT");
        repository.setTbSuffix("_ACCOUNT");
        return repository;
    }


    /**
     * 事物恢复策略
     *
     * @return
     */
    @Bean
    public RecoverConfig recoverConfig() {
        DefaultRecoverConfig config = new DefaultRecoverConfig();
        config.setMaxRetryCount(30);
        config.setRecoverDuration(120);
        config.setCronExpression("0 */1 * * * ?");
        return config;
    }
}
