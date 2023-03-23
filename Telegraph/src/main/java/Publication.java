/**
 * @author butrim
 */
public abstract class Publication {
    private final int id;
    private final String title;

    public Publication(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    /**
     * it's better not to have this method
     */
    public Dto toDto() {
        return new Dto(this);
    }

    public static class Article extends Publication {
        private final String text;

        public Article(int id, String title, String text) {
            super(id, title);
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public static class Video extends Publication {
        private final String sourceUrl;

        public Video(int id, String title, String sourceUrl) {
            super(id, title);
            this.sourceUrl = sourceUrl;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }
    }

    /**
     * этот объектом является отображение строки в файле на публикацию любого типа
     */
    public static final class Dto {
        public static final String HEADER = "id,title,text,sourceUrl";
        private final int id;
        private final String title;
        private final String text; // not null for articles
        private final String sourceUrl; // not null for videos

        public Dto(Publication publication) {
            this.id = publication.getId();
            this.title = publication.getTitle();
            this.text = publication instanceof Article
                    ? ((Article) publication).getText()
                    : null;
            this.sourceUrl = publication instanceof Video
                    ? ((Video) publication).getSourceUrl()
                    : null;
        }

        public static Dto cons(Publication publication) {
            return new Dto(publication);
        }

        public String asString() {
            return String.format(
                    "%s,%s,%s,%s",
                    id,
                    title,
                    text != null ? text : "",
                    sourceUrl != null ? sourceUrl : ""
            );
        }
    }
}
