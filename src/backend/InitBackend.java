package backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class InitBackend {
    public static void InitB() throws IOException, URISyntaxException {
        boolean exists = InitSongDir.songsFolderExists();
        Path songsPath = InitSongDir.ensureSongsFolder();

        FetchSettings.checkAndCreateSettings();
        int debugMode = FetchSettings.readSettings();

        if (debugMode == 1){
            System.out.println("debug is on"); 

            if (exists){
                System.out.println("songs directory is at: " + songsPath);
            }else{
                System.out.println("songs directory got created at: " + songsPath);
            }
        }
    }
}