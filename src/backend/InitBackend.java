package backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InitBackend {

    private static List<Path> songFiles = new ArrayList<>();

    public static List<Path> getSongFiles() {
        return songFiles;
    }

    public static void Init() throws IOException, URISyntaxException {
        boolean exists = InitSongDir.songsFolderExists();
        Path songsPath = InitSongDir.ensureSongsFolder();

        songFiles = FetchSongsInDir.fetchSongFiles();

        FetchSettings.checkAndCreateSettings();
        int debugMode = FetchSettings.readSettings();

        if (debugMode == 1) {
            System.out.println("debug is on");

            if (exists) {
                System.out.println("songs directory is at: " + songsPath);
            } else {
                System.out.println("songs directory got created at: " + songsPath);
            }

            System.out.println(songFiles.size() + " files found in /songs");
            for (Path song : songFiles) {
                System.out.println(" - " + song.getFileName());
            }
        }
    }
}