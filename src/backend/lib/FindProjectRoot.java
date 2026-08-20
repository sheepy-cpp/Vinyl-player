package backend.lib;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FindProjectRoot {
    
    public static Path findProjectRoot() throws URISyntaxException {
        Path classLocation = Paths.get(FindProjectRoot.class.getProtectionDomain()
            .getCodeSource().getLocation().toURI());

        Path current = classLocation.toAbsolutePath();
        while (current != null) {
            if (Files.isDirectory(current.resolve("src"))) {
                return current;
            }
            current = current.getParent();
        }
        throw new IllegalStateException("project root not found");
    }
}