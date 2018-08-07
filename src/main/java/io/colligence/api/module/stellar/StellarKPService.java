package io.colligence.api.module.stellar;

import io.colligence.api.PostLaunchExecutor;
import io.colligence.api.util.PrefixedLogger;
import org.springframework.stereotype.Service;
import org.stellar.sdk.KeyPair;

import javax.annotation.PostConstruct;

@Service
public class StellarKPService {
    private final PrefixedLogger logger = PrefixedLogger.getLogger(getClass());

    @PostConstruct
    private void initialize() {
        PostLaunchExecutor.addTask(this::keyPairTest);
    }

    private void keyPairTest() {
        KeyPair pair = KeyPair.random();

        logger.info(new String(pair.getSecretSeed()));
        logger.info(pair.getAccountId());
    }
}
