package backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class InitBackend {
    public static void InitB() throws IOException, URISyntaxException {
        boolean exists = InitSongDir.songsFolderExists(); // checks if /songs exists
        Path songsPath = InitSongDir.ensureSongsFolder(); // fetches path to /songs

        FetchSettings.checkAndCreateSettings();           // checks if settings json file exists
        int debugMode = FetchSettings.readSettings();     // reads out debug mode

        if (debugMode == 1){
            System.out.println("debug is on"); 
            // debug is off per default so theres no debug where it gets created or if it exists

            if (exists){
                System.out.println("songs directory is at: " + songsPath);
            }else{
                System.out.println("songs directory got created at: " + songsPath);
            }
        }

    }
}