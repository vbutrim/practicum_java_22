import java.nio.file.Paths;

/**
 * @author butrim
 */
public class Main {
    public static void main(String[] args) {
        PublicationStorage publicationStorage = new PublicationStorage.FileBacked(
                Paths.get("/Users/butrim/Work/teach/practicum_java_22/Telegraph/src/main/resources/publications_dump")
        );

        init(publicationStorage);
    }

    private static void init(PublicationStorage publicationStorage) {
        publicationStorage.add(
                new Publication.Article(
                        1,
                        "article#1",
                        "some content"
                )
        );

        publicationStorage.add(
                new Publication.Video(
                        2,
                        "video#1",
                        "some_content_url"
                )
        );

        publicationStorage.add(
                new Publication.Video(
                        3,
                        "video#2",
                        "some_content_url"
                )
        );
    }
}
