import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// abstract class TaskManagerTest<T extends TaskManager>
public abstract class PublicationStorageTest<TPublicationStorage extends PublicationStorage> {
    TPublicationStorage publicationStorage;

    @Test
    void shouldAddPublication() {
        // Given
        Publication.Article article = new Publication.Article(
                1,
                "article",
                "content"
        );

        // When
        publicationStorage.add(article);

        // Then
        Assertions.assertEquals(
                article,
                publicationStorage.getOrThrow(article.getId())
        );
    }

    @Test
    void shouldCreateSubtask() {
        // Given
        // 1: create epic
        // 2: create subtask

        // When

        // Then
    }
}
