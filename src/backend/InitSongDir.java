package backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import customlib.FindProjectRoot;

public class InitSongDir {

    public static Path ensureSongsFolder() throws IOException, URISyntaxException {
        Path projectRoot = FindProjectRoot.findProjectRoot();
        Path songsPath = projectRoot.resolve("songs");  // this finds the path to the songs directory using findProjectRoot()
        Files.createDirectories(songsPath);
        return songsPath;
    }

}