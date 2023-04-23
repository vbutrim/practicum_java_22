package telegraph.api;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import telegraph.Context;
import telegraph.publication.Publication;
import telegraph.publication.PublicationStorage;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class PublicationServer implements Closeable {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json;charset=utf-8";

    private static final String GET = "GET";
    private static final String POST = "POST";

    private static final String PUBLICATIONS_ENDPOINT = "/publications";

    private final PublicationStorage publicationStorage;
    private final HttpServer httpServer;

    public PublicationServer(
            int port,
            PublicationStorage publicationStorage)
            throws IOException
    {
        this.httpServer = HttpServer.create(new InetSocketAddress("localhost", port), 0);
        this.publicationStorage = publicationStorage;

        this.httpServer.createContext(PUBLICATIONS_ENDPOINT, this::publicationsEndpoint);
        this.httpServer.createContext("/health", this::healthEndpoint);
    }

    /**
     * GET: /publications -- get all publications (?)
     * GET: /publications/<publication_id> -- get publication by id
     * POST: /publications -- create publication (?)
     */
    private void publicationsEndpoint(HttpExchange httpExchange) {
        try {
            String method = httpExchange.getRequestMethod();

            String miscellaneousPath = httpExchange
                    .getRequestURI()
                    .getPath()
                    .substring(PUBLICATIONS_ENDPOINT.length());

            if (miscellaneousPath.isEmpty()) {
                if (method.equals(GET)) {
                    getPublications(httpExchange);
                    return;
                } else if (method.equals(POST)) {
                    createPublication(httpExchange);
                    return;
                } else {
                    badRequest(httpExchange);
                    return;
                }
            }

            if (method.equals(GET)) {
                OptionalInt publicationIdO = getPublicationIdOrNull(miscellaneousPath);

                if (publicationIdO.isEmpty()) {
                    badRequest(httpExchange);
                } else {
                    getPublication(httpExchange, publicationIdO.getAsInt());
                }
            } else {
                badRequest(httpExchange);
            }
        } catch (IOException e) {
            System.out.println("Error on performing request");
            e.printStackTrace();
        } finally {
            httpExchange.close();
        }
    }

    private void getPublications(HttpExchange httpExchange) throws IOException {
        try {
            List<Publication> publications = publicationStorage.getAll();

            writeJsonResponse(
                    httpExchange,
                    new PublicationsResponseDto(publications)
                            .asString()
            );
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            internalError(httpExchange);
        }
    }

    private void createPublication(HttpExchange httpExchange) throws IOException {
        String body = readBody(httpExchange);
        try {
            Publication publicationToCreate = PublicationToCreateInputDto.from(body)
                    .asPublication();

            Publication added = publicationStorage.add(publicationToCreate);
            writeJsonResponse(httpExchange, PublicationResponseDto.from(added).asString());
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            internalError(httpExchange);
        }
    }

    private void getPublication(HttpExchange httpExchange, int publicationId) throws IOException {
        try {
            Optional<Publication> publicationO = publicationStorage.getO(publicationId);

            if (publicationO.isEmpty()) {
                notFound(httpExchange);
                return;
            }

            writeJsonResponse(
                    httpExchange,
                    PublicationResponseDto
                            .from(publicationO.get())
                            .asString()
            );
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            internalError(httpExchange);
        }
    }

    private static OptionalInt getPublicationIdOrNull(String pathMiscellaneous) {
        if (!pathMiscellaneous.startsWith("/")) {
            return OptionalInt.empty();
        }

        try {
            return OptionalInt.of(Integer.parseInt(pathMiscellaneous.substring(1)));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }

    private void healthEndpoint(HttpExchange httpExchange) {
        try {
            if (!httpExchange.getRequestMethod().equals(GET)) {
                badRequest(httpExchange);
            }

            writeJsonResponse(httpExchange, "OK");
        } catch (Exception e) {
            System.out.println("Error on performing request");
            e.printStackTrace();
        } finally {
            httpExchange.close();
        }
    }

    public void start() {
        System.out.println("Starting server");
        httpServer.start();
        System.out.println("Server is started");
    }

    @Override
    public void close() {
        httpServer.stop(0);
        System.out.println("Server stopped");
    }

    private static String readBody(HttpExchange h) throws IOException {
        return new String(h.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }

    private static void writeJsonResponse(HttpExchange httpExchange, String stringifiedJson) throws IOException {
        writeResponse(httpExchange, APPLICATION_JSON, stringifiedJson);
    }

    @SuppressWarnings("SameParameterValue")
    private static void writeResponse(HttpExchange httpExchange, String contentType, String text) throws IOException {
        httpExchange.getResponseHeaders().add(CONTENT_TYPE, contentType);

        byte[] response = text.getBytes(StandardCharsets.UTF_8);
        httpExchange.sendResponseHeaders(200, response.length);
        httpExchange.getResponseBody().write(response);
    }

    private void badRequest(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(400, 0);
    }

    private void notFound(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(404, 0);
    }

    private void internalError(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(500, 0);
    }
}
