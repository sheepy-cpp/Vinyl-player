package backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import customlib.FindProjectRoot;

public class InitSongDir {

    public static boolean songsFolderExists() throws IOException, URISyntaxException {
        Path projectRoot = FindProjectRoot.findProjectRoot();
        Path songsPath = projectRoot.resolve("songs");
        return Files.exists(songsPath);
    }

    public static Path ensureSongsFolder() throws IOException, URISyntaxException {
        Path projectRoot = FindProjectRoot.findProjectRoot();
        Path songsPath = projectRoot.resolve("songs");
        Files.createDirectories(songsPath);
        return songsPath;
    }
}
