package net.hiskarma.stellarTest.module.stellar;

import net.hiskarma.stellarTest.PostLaunchExecutor;
import net.hiskarma.stellarTest.util.PrefixedLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
