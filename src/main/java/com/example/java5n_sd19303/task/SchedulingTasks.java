package com.example.java5n_sd19303.task;

import com.example.java5n_sd19303.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class SchedulingTasks {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:m:ss");
    private Logger logger = LoggerFactory.getLogger(SchedulingTasks.class);

    private final ProductService productService;

    public SchedulingTasks(ProductService productService) {
        this.productService = productService;
    }

    // 1.
    //@Scheduled(fixedRate = 2000)
    //public void scheduleTaskWithFixedRate() {
    //
    //    log.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    //}

    // 2.
    //@Scheduled(fixedDelay = 2000)
    //public void scheduleTaskWithFixedDelay() {
    //
    //    log.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    //}

    // 3.
    //@Scheduled(fixedRate = 2000, initialDelay = 5000)
    //public void scheduleTaskWithInitialDelay() {
    //
    //    log.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    //}

    // 4. cron
    @Scheduled(cron = "*/20 * * * * ?")
    public void scheduleTaskWithCronExpression() {

        log.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        log.info("Products in DB - {}", productService.getAllProducts().size());
    }




}
