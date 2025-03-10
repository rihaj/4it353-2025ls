package lab03;

import cz.vse.java.it353.lab03.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        log.info("Server started.");

        /*ServerSocket serverSocket = null;
        try {
            log.info("Creating server socket.");
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            log.error("Error occurred while creating server socket.", e);
        } finally {
            if (serverSocket != null) {
                try {
                    log.info("Closing server socket.");
                    serverSocket.close();
                } catch (IOException ex) {
                    log.error("Error occurred while closing server socket.", ex);
                }
            }
        }*/

        log.info("Creating server socket.");
        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

            boolean keepAlive = true;
            String username = null;

            while (keepAlive) {
                Message message = (Message) ois.readObject();
                String[] parts = message.getBody().split(" ", 2);

                String command = parts[0];
                String text = parts.length > 1 ? parts[1] : null;

                if ("QUIT".equals(command)) {
                    log.info("User sent QUIT, terminating connection.");
                    keepAlive = false;

                    Message response = new Message("OK");
                    oos.writeObject(response);
                } else if ("USER".equals(command)) {
                    log.info("Setting username to {}.", text);
                    username = text;
                    Message response = new Message("OK");
                    oos.writeObject(response);
                } else if ("MESSAGE".equals(command)) {
                    if (username == null || username.isBlank()) {
                        log.warn("Username not set, cannot accept message.");

                        Message response = new Message("ERR Username not set.");
                        oos.writeObject(response);
                    } else {
                        System.out.println(username + ": " + text);
                        Message response = new Message("OK");
                        oos.writeObject(response);
                    }
                } else {
                    log.warn("Unknown command: {}.", command);
                    Message response = new Message("ERR Unknown command.");
                    oos.writeObject(response);
                }
            }

        } catch (IOException e) {
            log.error("Error occurred in network communication.", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        log.info("Server terminated.");
    }
}
