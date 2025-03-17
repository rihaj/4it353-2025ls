package lab03;

import cz.vse.java.it353.lab03.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Listener implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    private ObjectInputStream ois;

    public Listener (ObjectInputStream ois) {
        this.ois = ois;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = (Message) ois.readObject();
                System.out.println(message.getTimestamp() + ": " + message.getBody());
            }
        } catch (IOException e) {
            log.error("Exception occurred while listening for incoming communication.", e);
        } catch (ClassNotFoundException e) {
            log.error("Exception occurred while deserializing incoming message.", e);
        }
    }

}
