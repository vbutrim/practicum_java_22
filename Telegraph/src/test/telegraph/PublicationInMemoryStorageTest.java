package telegraph;

import org.junit.jupiter.api.BeforeEach;
import telegraph.publication.PublicationStorage;

class PublicationInMemoryStorageTest extends PublicationStorageTest<PublicationStorage.InMemory> {
    @BeforeEach
    void setUp() {
        publicationStorage = new PublicationStorage.InMemory();
    }
}