package telegraph;

import telegraph.api.PublicationServer;

import java.io.IOException;

public class PublicationServerRunner {
    public static void main(String[] args) throws IOException {
        PublicationServer publicationServer = Context.getPublicationServer();
        publicationServer.start();
    }
}
