package cz.vse.java.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.vse.java.dto.Message;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Server {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    public static void main(String[] args) throws IOException {
        Thread eventProcessor = new Thread( Server::listenForEvents );
        eventProcessor.start();

        try {
            System.in.read();
        } finally {
            eventProcessor.interrupt();
        }
    }

    private static void listenForEvents() {
        Path messageDir = Paths.get("c:","projects", "swamp", "temp");
        messageDir.toFile().mkdirs();

        WatchKey key = null;
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            key = messageDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

            while (!Thread.interrupted()) {
                watcher.take();

                key.pollEvents().stream()
                   .filter( event -> StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind()) )
                   .map( event -> (WatchEvent<Path>) event )
                   .map( WatchEvent::context )
                   .map( context -> messageDir.resolve(context).toFile() )
                   .filter( File::isFile )
                   .forEach( Server::processMessage );

                if (!key.reset()) {
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Exception occurred while processing file system events.");
            e.printStackTrace();

            Thread.currentThread().interrupt();
        } finally {
            if (key != null) {
                key.cancel();
            }
        }
    }

    private static void processMessage(File messageFile) {
        try {
            System.out.print("Receiving message from file: " + messageFile.getName() + ", ");

            Thread.sleep(100);  // Wait for a while to allow client finish writing to file

            Message msg = OBJECT_MAPPER.readValue(messageFile, Message.class);
            System.out.println("content: " + msg);
        } catch (IOException e) {
            System.out.println("Exception occurred while reading message.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
