package backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class InitBackend {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Path songsPath = InitSongDir.ensureSongsFolder(); // fetches /songs directory and creates it if not available

        System.out.println(songsPath);
    }
}