import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PublicationFileBackedStorageTest extends PublicationStorageTest<PublicationStorage.FileBacked> {

    private static final Path TEST_FILE = Path.of(
            "C:\\work\\code\\practicum_java_22\\Telegraph\\src\\main\\resources\\publications_test"
    );

    @BeforeEach
    void setUp() {
        publicationStorage = new PublicationStorage.FileBacked(TEST_FILE);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(TEST_FILE);
    }

    @Test
    void shouldSave() throws IOException {
        // Given

        // When
        publicationStorage.add(
                new Publication.Article(
                        1,
                        "article",
                        "content"
                )
        );

        // Then
        List<String> result = Files.readAllLines(TEST_FILE);
        Assertions.assertEquals(
                List.of(
                        "id,title,text,sourceUrl",
                        "1,article,content,"
                ),
                result
        );
    }

}
