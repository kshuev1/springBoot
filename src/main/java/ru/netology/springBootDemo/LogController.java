package ru.netology.springBootDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private static final Logger logger =
            LoggerFactory.getLogger(LogController.class);

    @GetMapping("/log")
    public String generateLog() {

        logger.info("Application received request");

        logger.debug("Debug information");

        return "Log generated";
    }

    @GetMapping("/error-log")
    public String generateErrorLog() {

        logger.warn("Test warning message");

        return "Warning log generated";
    }
}