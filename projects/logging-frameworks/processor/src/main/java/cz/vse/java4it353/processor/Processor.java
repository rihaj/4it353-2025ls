package cz.vse.java4it353.processor;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Processor {
    private static final Logger log = LogManager.getLogger(Processor.class);

    public static void process() {
        System.out.println("Processor is using old Log4J library.");
        log.info("Processor is using old Log4J library.");
    }
}
