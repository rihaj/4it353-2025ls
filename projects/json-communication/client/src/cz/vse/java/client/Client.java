package cz.vse.java.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.vse.java.dto.Message;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

public class Client {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    public static void main(String[] args) throws IOException {
        Thread messageGenerator = new Thread( Client::generateMessages );
        messageGenerator.start();

        try {
            System.in.read();
        } finally {
            messageGenerator.interrupt();
        }
    }

    private static void generateMessages() {
        try {
            Path messageDir = Paths.get("c:","projects", "swamp", "temp");
            messageDir.toFile().mkdirs();

            while (!Thread.interrupted()) {
                Message message = new Message();
                message.setUserId(randomUser());
                message.setText(randomMessage());

                System.out.println("Sending message: " + message);
                OBJECT_MAPPER.writeValue(messageDir.resolve(UUID.randomUUID().toString() + ".json").toFile(), message);

                Thread.sleep(5000);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Exception occurred while generating messages.");
            e.printStackTrace();

            Thread.currentThread().interrupt();
        }
    }

    private static String randomMessage() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < RANDOM_GENERATOR.nextInt(90) + 10; i++) {
            sb.append(ALLOWED_CHARS.charAt(RANDOM_GENERATOR.nextInt(ALLOWED_CHARS.length())));
        }

        return sb.toString();
    }

    private static String randomUser() {
        return "USER_" + (RANDOM_GENERATOR.nextInt(5) + 1);
    }
}
