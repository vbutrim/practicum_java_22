import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author butrim
 *
 * Tasks to do:
 * store our publications in the file and read from this file.
 * <br/>
 * What would we like to cover:
 * 1) Exception
 * 2) Streams (потоки)
 */
public interface PublicationStorage {

    void save(Publication publication);

    Publication getOrThrow(int id);

    class InMemory implements PublicationStorage {
        protected final Map<Integer, Publication> publications;

        public InMemory() {
            this.publications = new HashMap<>();
        }

        @Override
        public void save(Publication publication) {
            publications.put(publication.getId(), publication);
        }

        @Override
        public Publication getOrThrow(int id) {
            if (!publications.containsKey(id)) {
                throw new RuntimeException("publication not found");
            }

            return publications.get(id);
        }
    }

    class FileBacked extends InMemory {
        private final Path path;

        public FileBacked(Path path) {
            this.path = path;
        }

        @Override
        public void save(Publication publication) {
            super.save(publication);
            save();
        }

        private void save() {
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
}
