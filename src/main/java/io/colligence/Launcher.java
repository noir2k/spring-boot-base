package io.colligence;

import io.colligence.config.CommonDefs;
import io.colligence.util.PrefixedLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.AsyncRestTemplate;

@SpringBootApplication
@EnableAsync
public class Launcher {
    private static final PrefixedLogger logger = PrefixedLogger.getLogger(Launcher.class);

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    public static void shutdown(CommonDefs.EXITCODE code) {
        logger.warn("Shutdown : {} ({})", code.name(), code.getCode());
        System.exit(code.getCode());
    }

    @Bean
    public AsyncRestTemplate asyncRestTemplate(){
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
        asyncRestTemplate.setAsyncRequestFactory(new Netty4ClientHttpRequestFactory());
        return asyncRestTemplate;
    }
}
