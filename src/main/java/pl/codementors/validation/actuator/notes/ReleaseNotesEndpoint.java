package pl.codementors.validation.actuator.notes;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@WebEndpoint(id = "release-notes")
public class ReleaseNotesEndpoint {

    private static final String RELEASE_NOTES_LINK = "/actuator/release-notes/%s";
    private static final URL RELEASE_NOTES_DIRECTORY = ClassLoader.getSystemResource("release-notes");
    private Map<String, String> releaseNotes = new HashMap<>();

    @PostConstruct
    void init() throws IOException, URISyntaxException {
        this.releaseNotes = Files.list(Path.of(RELEASE_NOTES_DIRECTORY.toURI()))
                .collect(Collectors.toMap(
                        p -> p.getFileName().toString().replace(".txt", ""),
                        this::readFileContent)
                );
    }

    @ReadOperation
    public List<ReleaseNote> getReleaseNotes() {
        return releaseNotes.keySet()
                .stream()
                .map(version -> new ReleaseNote(version, String.format(RELEASE_NOTES_LINK, version)))
                .collect(Collectors.toList());
    }

    @ReadOperation
    public String getReleaseNotes(@Selector String version) {
        return releaseNotes.get(version);
    }

    private String readFileContent(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
