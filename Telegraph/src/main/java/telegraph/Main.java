package telegraph;

import telegraph.api.PublicationServer;
import telegraph.keyvalueserver.KVServer;
import telegraph.publication.Publication;
import telegraph.publication.PublicationStorage;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author butrim
 */
public class Main {
    public static void main(String[] args) throws IOException {
        PublicationServer publicationServer = Context.getPublicationServer();
        publicationServer.start();

        KVServer kvServer = Context.getKvServer();
        kvServer.start();
    }

    private static void init(PublicationStorage publicationStorage) {
        publicationStorage.add(
                new Publication.Article(
                        1,
                        "article#1",
                        "some content",
                        LocalDateTime.of(2023, 4, 1, 12, 0)
                )
        );

        publicationStorage.add(
                new Publication.Video(
                        2,
                        "video#1",
                        "some_content_url",
                        LocalDateTime.of(2023, 4, 1, 15, 0)
                )
        );

        publicationStorage.add(
                new Publication.Video(
                        3,
                        "video#2",
                        "some_content_url",
                        LocalDateTime.of(2023, 4, 2, 12, 0)
                )
        );
    }
}
