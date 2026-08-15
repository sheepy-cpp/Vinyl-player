//package backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Path songsPath = InitSongs.ensureSongsFolder();

        System.out.println(songsPath);
    }
}