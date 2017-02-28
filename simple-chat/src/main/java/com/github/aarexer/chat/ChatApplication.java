package com.github.aarexer.chat;

import com.github.aarexer.chat.net.Client;
import com.github.aarexer.chat.net.NetworkConnection;
import com.github.aarexer.chat.net.Server;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatApplication extends Application {
    private boolean isServer = false;
    private final TextArea texts = new TextArea();
    private NetworkConnection connection = isServer ? createServer() : createClient();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        connection.start();
    }

    @Override
    public void stop() throws Exception {
        connection.close();
    }

    private Parent createContent() {
        final TextField field = new TextField();

        field.setOnAction(e -> {
            String message = isServer ? "Server" : "Client";
            message += field.getText();

            field.clear();

            texts.appendText(message);

            try {
                connection.send(message);
            } catch (Exception e1) {
                texts.appendText("Failed to send");
            }
        });
        final VBox vBox = new VBox(15, texts, field);
        vBox.setPrefSize(640, 640);
        return vBox;
    }

    private Server createServer() {
        return new Server(55555, data -> {
            Platform.runLater(() -> {
                texts.appendText(data.toString() + System.lineSeparator());
            });
        });
    }

    private Client createClient() {
        return new Client("127.0.0.1", 55555, data -> {
            texts.appendText(data.toString() + System.lineSeparator());
        });
    }
}
