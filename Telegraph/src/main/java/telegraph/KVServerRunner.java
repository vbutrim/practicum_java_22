package telegraph;

import telegraph.keyvalueserver.KVServer;

import java.io.IOException;

public class KVServerRunner {
    public static void main(String[] args) throws IOException {
        KVServer kvServer = Context.getKvServer();
        kvServer.start();
    }
}
