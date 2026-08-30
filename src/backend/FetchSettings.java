package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import lib.FindProjectRoot;

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

}