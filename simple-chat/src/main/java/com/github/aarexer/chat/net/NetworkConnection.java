package com.github.aarexer.chat.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection implements AutoCloseable {
    private Consumer<Serializable> onRecieveCallback;
    private ConnectionThread connThread;

    public NetworkConnection(Consumer<Serializable> onRecieveCallback) {
        this.onRecieveCallback = onRecieveCallback;
        connThread = new ConnectionThread();
        connThread.setDaemon(true);
    }

    public void start() {
        connThread.start();
    }

    public void send(Serializable data) throws Exception {
        connThread.out.writeObject(data);
    }

    @Override
    public void close() throws Exception {
        connThread.socket.close();
    }

    protected abstract boolean isServer();

    protected abstract String getIP();

    protected abstract int getPort();

    private class ConnectionThread extends Thread {
        private Socket socket;
        private ObjectOutputStream out;

        @Override
        public void run() {
            try (ServerSocket serverSocket = isServer() ? new ServerSocket(getPort()) : null;
                 Socket socket = isServer() ? serverSocket.accept() : new Socket(getIP(), getPort());
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                this.socket = socket;
                this.out = out;

                socket.setTcpNoDelay(true);

                while (true) {
                    Serializable data = (Serializable) in.readObject();
                    onRecieveCallback.accept(data);
                }

            } catch (Exception e) {
                onRecieveCallback.accept("Connection closed");
            }
        }
    }
}
