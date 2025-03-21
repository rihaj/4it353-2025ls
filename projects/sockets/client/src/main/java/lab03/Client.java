package lab03;

import cz.vse.java.it353.lab03.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        log.info("Client started.");

        log.info("Creating client socket and connecting to server.");
        try (Scanner scanner = new Scanner(System.in);
             Socket socket = new Socket("127.0.0.1", 8080);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            Thread listenerThread = new Thread(new Listener(ois));
            listenerThread.start();

            boolean keepAlive = true;

            while (keepAlive) {
                log.debug("Waiting for user input.");
                String data = scanner.nextLine();

                if ("QUIT".equals(data)) {
                    log.debug("User entered QUIT, client termination started.");
                    keepAlive = false;
                }

                log.debug("Sending message to server: {}", data);

                Message m = new Message(data);
                oos.writeObject(m);
            }

        } catch (UnknownHostException e) {
            log.error("Error occurred while connecting to server.", e);
        } catch (IOException e) {
            log.error("Error occurred in network communication.", e);
        }

        log.info("Client terminated.");
    }
}
