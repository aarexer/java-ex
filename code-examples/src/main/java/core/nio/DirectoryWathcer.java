package core.nio;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

public class DirectoryWathcer {
    private Path directory;
    private DirectoryStream.Filter<Path> dirFilter;
    private final Set<String> ignore = new HashSet<>();
    private WatchService watchService;

    public DirectoryWathcer(String dir) {
        this.directory = Paths.get(dir);
        if (!Files.isDirectory(directory))
            throw new IllegalArgumentException("Can't initialize watcher service on file: " + dir);

        initWatchService();
        dirFilter = entry -> !ignore.contains(entry.getFileName().toString());
    }

    public DirectoryWathcer(String dir, DirectoryStream.Filter<Path> dirFilter) {
        this.directory = Paths.get(dir);
        if (!Files.isDirectory(directory))
            throw new IllegalArgumentException("Can't initialize watcher service on file: " + dir);

        initWatchService();
        this.dirFilter = dirFilter;
    }

    private void initWatchService() {
        try {
            watchService = directory.getFileSystem().newWatchService();
            directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addIgnoreName(String name) {
        return ignore.add(name);
    }

    public void startMonitoring() {
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
