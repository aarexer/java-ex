package utils.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DirectoryWatcher implements Runnable {
    private Path directory;
    private DirectoryStream.Filter<Path> dirFilter;
    private final Set<String> ignore = new HashSet<>();
    private WatchService watchService;

    public DirectoryWatcher(String dir) {
        this.directory = Paths.get(dir);
        if (!Files.isDirectory(directory))
            throw new IllegalArgumentException("Can't initialize watcher service on file: " + dir);

        dirFilter = entry -> !ignore.contains(entry.getFileName().toString());
    }

    public DirectoryWatcher(String dir, DirectoryStream.Filter<Path> dirFilter) {
        this.directory = Paths.get(dir);
        if (!Files.isDirectory(directory))
            throw new IllegalArgumentException("Can't initialize watcher service on file: " + dir);

        this.dirFilter = dirFilter;
    }

    private void initWatchService() {
        try {
            watchService = directory.getFileSystem().newWatchService();
            directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            new Thread(this::startMonitoring).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            //some work
        }
    }

    private List<String> fileList() {
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory, dirFilter)) {
            for (Path path : directoryStream) {
                fileNames.add(path.toString());
            }
        } catch (IOException ignored) {
        }
        return fileNames;
    }

    public boolean addIgnoreName(String name) {
        return ignore.add(name);
    }

    private void startMonitoring() {
        while (true) {
            try {
                WatchKey key = watchService.take();
                for (WatchEvent event : key.pollEvents())
                    switch (event.kind().name()) {
                        case "ENTRY_CREATE":
                            System.out.println("File " + event.context()
                                    + " is created!");
                            break;
                    }
                key.reset();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
