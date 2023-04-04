import org.junit.jupiter.api.BeforeEach;

class PublicationInMemoryStorageTest extends PublicationStorageTest<PublicationStorage.InMemory> {
    @BeforeEach
    void setUp() {
        publicationStorage = new PublicationStorage.InMemory();
    }
}