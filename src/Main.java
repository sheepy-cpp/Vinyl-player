import backend.FetchSettings;
import java.io.IOException;
import java.net.URISyntaxException;
import org.json.*;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        FetchSettings.checkAndCreateSettings();
    }
}