import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AocClient {
    int day;
    int year;
    private final String sessionKey;

    public AocClient(int year, int day) {
        this.year = year;
        this.day = day;

        this.sessionKey = ConfigLoader.getInstance().properties.getProperty("SESSION_KEY");
    }

    public String fetchPuzzle() {
        try (var client = HttpClient.newHttpClient()) {
            var request = HttpRequest.newBuilder(
                    URI.create("https://adventofcode.com/%d/day/%d/input".formatted(this.year, this.day))
            ).header("accept", "text/plain").header("Cookie", "session=%s".formatted(this.sessionKey)).GET().build();

            try {
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());

                return response.body();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
