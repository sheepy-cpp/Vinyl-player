package backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.List;

public class FetchSongsInDir {
    public static List<Path> fetchSongFiles() {
        try {
            Path songsPath = InitSongDir.ensureSongsFolder();

            try (Stream<Path> stream = Files.list(songsPath)) {
                return stream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase().endsWith(".mp3"))
                    .toList();
            }
        } catch (IOException | URISyntaxException e) {
            System.err.println("Error reading files: " + e.getMessage());
            return List.of();
        }
    }
}
