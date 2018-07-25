package net.hiskarma.stellarTest;

import net.hiskarma.stellarTest.util.PrefixedLogger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class PostLaunchExecutor implements ApplicationListener<ApplicationReadyEvent> {
    private static final PrefixedLogger logger = PrefixedLogger.getLogger(PostLaunchExecutor.class);

    private static final Queue<Runnable> taskQueue = new LinkedList<>();

    private static boolean executed = false;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Application launched. Initiating post launch executor.");
        execute();
    }

    public static void addTask(Runnable task) {
        if (executed) {
            logger.warn("PostLaunchExecutor already executed. addTask ignored.");
            return;
        }
        taskQueue.offer(task);
    }

    private static void execute() {
        executed = true;
        while (!taskQueue.isEmpty()) {
            try {
                Runnable r = taskQueue.poll();
                r.run();
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}
