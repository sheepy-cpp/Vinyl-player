package backend;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import customlib.FindProjectRoot;
import org.json.JSONObject;
import org.json.JSONTokener;


public class FetchSettings {

    private static final String FILE_NAME = "VinylSettings.json";

    public static void checkAndCreateSettings() {
        try {
            Path projectRoot = FindProjectRoot.findProjectRoot();
            Path settingsPath = projectRoot.resolve(FILE_NAME);
            File file = settingsPath.toFile();

            if (file.exists()) {
                return;
            }

            if (file.createNewFile()) {
                try (FileWriter writer = new FileWriter(file)) {
                    // update this template if changes to the structure of settings.json get made
                    String template = """
                    {
                        "debugMode": "false",
                        "language": "eng"
                    }
                    """;

                    writer.write(template);
                }
            }
        } catch (IOException | URISyntaxException e) {
            System.err.println("error creating " + FILE_NAME + ": " + e.getMessage());
        }
    }

    public static int readSettings(){
        try {
            Path projectRoot = FindProjectRoot.findProjectRoot();
            Path settingsPath = projectRoot.resolve(FILE_NAME);
            File file = settingsPath.toFile();

            try (FileReader reader = new FileReader(file)) {
                JSONObject json = new JSONObject(new JSONTokener(reader));

                String debugModeStr = json.getString("debugMode");
                boolean debugMode = Boolean.parseBoolean(debugModeStr);

                return debugMode ? 1 : 0;
            }

        } catch (IOException | URISyntaxException e) {
            System.err.println("error reading " + FILE_NAME + ": " + e.getMessage());
            return -1; 
        }
    }

}