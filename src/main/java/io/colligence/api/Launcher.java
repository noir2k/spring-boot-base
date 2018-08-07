package io.colligence.api;

import io.colligence.api.config.CommonDefs;
import io.colligence.api.persistence.repository.UserRepository;
import io.colligence.api.util.PrefixedLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Launcher {
    private static final PrefixedLogger logger = PrefixedLogger.getLogger(Launcher.class);

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    public static void shutdown(CommonDefs.EXITCODE code) {
        logger.warn("Shutdown : {} ({})", code.name(), code.getCode());
        System.exit(code.getCode());
    }
}
