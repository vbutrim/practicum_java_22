package telegraph.publication;

import com.google.gson.Gson;
import telegraph.Context;
import telegraph.api.PublicationResponseDto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author butrim
 * <p>
 * Tasks to do:
 * store our publications in the file and read from this file.
 * <br/>
 * What would we like to cover:
 * 1) Exception
 * 2) Streams (потоки)
 */
public interface PublicationStorage {

    Publication add(Publication publication);

    Optional<Publication> getO(int id);

    Publication getOrThrow(int id);

    List<Publication> getAll();

    class InMemory implements PublicationStorage {
        protected final Map<Integer, Publication> publications;

        public InMemory() {
            this.publications = new HashMap<>();
        }

        @Override
        public Publication add(Publication publication) {
            publications.put(publication.getId(), publication);
            return publication;
        }

        @Override
        public Optional<Publication> getO(int id) {
            return Optional.ofNullable(publications.get(id));
        }

        @Override
        public Publication getOrThrow(int id) {
            if (!publications.containsKey(id)) {
                throw new RuntimeException("publication not found");
            }

            return publications.get(id);
        }

        @Override
        public List<Publication> getAll() {
            return Collections.unmodifiableList(
                    new LinkedList<>(publications.values())
            );
        }
    }

    class FileBacked extends InMemory {
        private final Path path;

        public FileBacked(Path path) {
            this.path = path;
        }

        @Override
        public Publication add(Publication publication) {
            super.add(publication);
            save();
            return publication;
        }

        protected void save() {
            try (BufferedWriter writer = getWriter()) {
                savePublicationsToFile(writer);
            } catch (IOException e) {
                throw new RuntimeException("Error on saving to file", e);
            }
        }

        private BufferedWriter getWriter() throws IOException {
            return new BufferedWriter(
                    new FileWriter(path.toFile())
            );
        }

        private void savePublicationsToFile(BufferedWriter writer) throws IOException {
            // 1: dump header
            writer.write(Publication.Dto.HEADER);
            writer.write(System.lineSeparator());

            // 2: for each publication, dump it to the writer
            for (Publication publication : publications.values()) {
                writer.write(Publication.Dto.cons(publication).asString());
                writer.write("\n");
            }
        }
    }

    class Http extends FileBacked {
        private static final String STATE_KEY = "publication-http-storage-state";

        private final KVClient kvClient;

        public Http(String kvUrl) {
            super(null);
            this.kvClient = new KVClient(kvUrl);
            load();
        }

        private void load() {
            try {
                String value = kvClient.loadOrThrow(STATE_KEY);
                StateDto stateDto = StateDto.from(value);
                stateDto.publications
                        .stream()
                        .map(PublicationResponseDto::asPublication)
                        .forEach(publication -> this.publications.put(publication.getId(), publication));
            } catch (Exception e) {
                System.out.println("State not found");
            }
        }

        @Override
        protected void save() {
            try {
                kvClient.saveOrThrow(STATE_KEY, new StateDto(this.publications.values(), LocalDateTime.now()).asString());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to save");
            }
        }

        private static final class StateDto {
            private static final Gson GSON = Context.getGson();

            private final List<PublicationResponseDto> publications;
            private final LocalDateTime updateTime;

            private StateDto(
                    Collection<Publication> publications,
                    LocalDateTime updateTime) {
                this.publications = publications
                        .stream()
                        .map(PublicationResponseDto::from)
                        .collect(Collectors.toList());
                this.updateTime = updateTime;
            }

            private static StateDto from(String json) {
                return GSON.fromJson(json, StateDto.class);
            }

            private String asString() {
                return GSON.toJson(this);
            }
        }
    }
}
