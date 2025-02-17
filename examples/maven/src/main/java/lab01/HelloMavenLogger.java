package lab01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloMavenLogger {
    private static final Logger log = LoggerFactory.getLogger(HelloMavenLogger.class);

    public static void main(String[] args) {
        log.error("Hello Maven (Logger)!");
    }

}
