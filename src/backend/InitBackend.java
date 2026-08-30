package backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class InitBackend {
    public static void InitB() throws IOException, URISyntaxException {
        Path songsPath = InitSongDir.ensureSongsFolder(); // fetches /songs directory and creates it if not available


        FetchSettings.checkAndCreateSettings();
        int debugMode = FetchSettings.readSettings();

        if (debugMode == 1){
            System.out.println("debug is on");
            System.out.println(songsPath);
        }
    }
}