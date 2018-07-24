package net.hiskarma.stellarTest.module.stellar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.stellar.sdk.KeyPair;

import javax.annotation.PostConstruct;

@Service
public class StellarKPService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    private void initialize() {
        KeyPair pair = KeyPair.random();

        logger.info(new String(pair.getSecretSeed()));
        logger.info(pair.getAccountId());
    }
}
