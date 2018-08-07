package io.colligence;

import io.colligence.config.CommonDefs;
import io.colligence.persistence.repository.UserRepository;
import io.colligence.util.PrefixedLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Launcher {
    private static final PrefixedLogger logger = PrefixedLogger.getLogger(Launcher.class);

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    public static void shutdown(CommonDefs.EXITCODE code) {
        logger.warn("Shutdown : {} ({})", code.name(), code.getCode());
        System.exit(code.getCode());
    }
}
