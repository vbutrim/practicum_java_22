package telegraph.api;

import com.google.gson.Gson;
import telegraph.Context;
import telegraph.publication.Publication;

import java.util.List;
import java.util.stream.Collectors;

public class PublicationsResponseDto {
    private static final Gson GSON = Context.getGson();

    private final List<PublicationResponseDto> publications;

    public PublicationsResponseDto(List<Publication> publications) {
        this.publications = publications
                .stream()
                .map(PublicationResponseDto::from)
                .collect(Collectors.toList());
    }

    public String asString() {
        return GSON.toJson(this);
    }
}
