package cz.vse.java4it353.worker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Worker {
    private static final Log log = LogFactory.getLog(Worker.class);

    public static void work() {
        System.out.println("Worker is using JCL (Apache Commons Logging) library.");
        log.info("Worker is using JCL (Apache Commons Logging) library.");
    }
}
