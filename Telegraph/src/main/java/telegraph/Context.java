package telegraph;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import telegraph.api.PublicationServer;
import telegraph.config.LocalDateTimeAdapter;
import telegraph.keyvalueserver.KVServer;
import telegraph.publication.PublicationStorage;

import java.io.IOException;
import java.time.LocalDateTime;

public abstract class Context {
    private static final int PUBLICATION_SERVER_PORT = 8080;
    private static final int KEY_VALUE_SERVER_PORT = 8078;
    private static final String KEY_VALUE_URL = "http://localhost:" + KEY_VALUE_SERVER_PORT + "/";

    private static PublicationStorage publicationStorage;
    private static PublicationServer publicationServer;
    private static KVServer kvServer;
    private static Gson gson;

    public static PublicationStorage getPublicationStorage() {
        if (publicationStorage == null) {
/*            publicationStorage = new PublicationStorage.FileBacked(
                    Path.of("C:\\work\\code\\practicum_java_22\\Telegraph\\src\\main\\resources\\publications_dump")
            );*/

            publicationStorage = new PublicationStorage.Http(KEY_VALUE_URL);
        }
        return publicationStorage;
    }

    public static PublicationServer getPublicationServer() throws IOException {
        if (publicationServer == null) {
            publicationServer = new PublicationServer(
                    PUBLICATION_SERVER_PORT,
                    getPublicationStorage()
            );
        }

        return publicationServer;
    }

    public static Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
            gson = gsonBuilder.create();
        }

        return gson;
    }

    public static KVServer getKvServer() throws IOException {
        if (kvServer == null) {
            kvServer = new KVServer(KEY_VALUE_SERVER_PORT);
        }
        return kvServer;
    }
}
