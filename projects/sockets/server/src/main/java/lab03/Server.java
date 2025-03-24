package lab03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    private static Set<Connection> connections = new HashSet<>();
    private static Deque<String> messages = new LinkedList<>();

    public static void main(String[] args) {
        log.info("Server started.");

        Thread sender = new Thread(() -> {
            log.info("Starting sender thread.");

            try {
                while (true) {
                    synchronized (messages) {
                        while (messages.isEmpty()) {
                            log.debug("Uspavam sender vlakno.");
                            messages.wait();
                        }

                        log.debug("Odesilam zpravu.");
                        String msg = messages.poll();

                        Set<Connection> copy = null;
                        synchronized (connections) {
                            copy = new HashSet<>(connections);
                        }

                        for (Connection c : copy) {
                            try {
                                c.sendMessage(msg);
                            } catch (IOException e) {
                                log.error("Exception occurred while sending message to client.", e);
                            }
                        }

                    }
                }
            } catch (InterruptedException e) {
                log.error("Interrupted ...", e);
            } finally {
                log.info("Terminating sender thread.");
            }
        });
        sender.start();

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                Socket socket = serverSocket.accept();

                Connection connection = new Connection(socket, connections);

                synchronized (connections) {
                    connections.add(connection);
                }

                Thread thread = new Thread(connection);
                thread.start();
            }
        } catch (IOException e) {
            log.error("Error occurred in network communication.", e);
        }

        log.info("Server terminated.");
    }

    public static void sendMessage(String message) {
        synchronized (messages) {
            messages.add(message);
            log.debug("Probouzim sender vlakno.");
            messages.notifyAll();
        }
    }
}
