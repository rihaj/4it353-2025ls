package lab03;

import cz.vse.java.it353.lab03.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Connection implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Connection.class);

    private Socket socket;
    private Set<Connection> connections = new HashSet<>();

    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public Connection(Socket socket, Set<Connection> connections) {
        this.socket = socket;
        this.connections = connections;

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        Message msg = new Message("MESSAGE " + message);
        oos.writeObject(msg);
    }

    @Override
    public void run() {
        log.info("Starting new connection.");
        try {
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

                        Server.sendMessage(username + ": " + text);
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
        } finally {
            try {
                synchronized (connections) {
                    connections.remove(this);
                }
                socket.close();
            } catch (IOException ex) {
                log.debug("Exception occurred while closing socket.", ex);
            }
        }
    }

}
