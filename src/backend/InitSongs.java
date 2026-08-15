//package backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InitSongs {

    public static Path ensureSongsFolder() throws IOException, URISyntaxException {
        Path projectRoot = findProjectRoot();
        Path songsPath = projectRoot.resolve("songs");      // this finds the path to the songs directory using findProjectRoot()
        Files.createDirectories(songsPath);
        return songsPath;
    }

    private static Path findProjectRoot() throws URISyntaxException {
        //place of running class file
        Path classLocation = Paths.get(InitSongs.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI());

        //goes up directories
        Path current = classLocation.toAbsolutePath();
        while (current != null) {
            if (Files.isDirectory(current.resolve("src"))) {
                return current;
            }
            current = current.getParent();
        }
        throw new IllegalStateException("project root not found");
    }
}