package telegraph.api;

import com.google.gson.Gson;
import telegraph.Context;
import telegraph.publication.Publication;
import telegraph.publication.PublicationType;

import java.time.LocalDateTime;

public class PublicationResponseDto {
    private static final Gson GSON = Context.getGson();

    private final int id;
    private final PublicationType type;
    private final String title;
    private final LocalDateTime addTime;
    private final String text; // nullable
    private final String sourceUrl; // nullable

    public PublicationResponseDto(Publication.Article publication) {
        this.id = publication.getId();
        this.type = publication.getType();
        this.title = publication.getTitle();
        this.addTime = publication.getAddTime();
        this.text = publication.getText();
        this.sourceUrl = null;
    }

    public PublicationResponseDto(Publication.Video publication) {
        this.id = publication.getId();
        this.type = publication.getType();
        this.title = publication.getTitle();
        this.addTime = publication.getAddTime();
        this.text = null;
        this.sourceUrl = publication.getSourceUrl();
    }

    public static PublicationResponseDto from(Publication publication) {
        switch (publication.getType()) {
            case ARTICLE:
                return new PublicationResponseDto((Publication.Article) publication);
            case VIDEO:
                return new PublicationResponseDto((Publication.Video) publication);
            default:
                throw new IllegalStateException();
        }
    }

    public Publication asPublication() {
        switch (type) {
            case ARTICLE:
                return new Publication.Article(
                        id,
                        title,
                        text,
                        addTime
                );
            case VIDEO:
                return new Publication.Video(
                        id,
                        title,
                        sourceUrl,
                        addTime
                );
            default:
                throw new IllegalArgumentException();
        }
    }

    public String asString() {
        return GSON.toJson(this);
    }
}
