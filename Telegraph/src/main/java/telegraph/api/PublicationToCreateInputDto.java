package telegraph.api;

import com.google.gson.Gson;
import telegraph.Context;
import telegraph.publication.Publication;
import telegraph.publication.PublicationType;

import java.time.LocalDateTime;
import java.util.UUID;

public class PublicationToCreateInputDto {
    private static final Gson GSON = Context.getGson();

    private final PublicationType type;
    private final String title;
    private final String text;
    private final String sourceUrl;

    public PublicationToCreateInputDto(
            PublicationType type,
            String title,
            String text,
            String sourceUrl)
    {
        this.type = type;
        this.title = title;
        this.text = text;
        this.sourceUrl = sourceUrl;
    }

    public static PublicationToCreateInputDto from(String json) {
        return GSON.fromJson(json, PublicationToCreateInputDto.class);
    }

    /**
     * @implNote for the sake of simplicity, id is generated here. It's much-much better to use IdGenerator in TaskManager
     */
    public Publication asPublication() {
        int id = UUID.randomUUID().hashCode();
        LocalDateTime now = LocalDateTime.now();

        switch (type) {
            case ARTICLE:
                return new Publication.Article(
                        id,
                        title,
                        text,
                        now
                );
            case VIDEO:
                return new Publication.Video(
                        id,
                        title,
                        sourceUrl,
                        now
                );
            default:
                throw new IllegalArgumentException();
        }
    }
}
