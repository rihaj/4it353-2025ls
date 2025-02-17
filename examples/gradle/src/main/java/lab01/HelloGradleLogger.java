package lab01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloGradleLogger {
    private static final Logger log = LoggerFactory.getLogger(HelloGradleLogger.class);

    public static void main(String[] args) {
        log.error("Hello Gradle (Logger)!");
    }

}
