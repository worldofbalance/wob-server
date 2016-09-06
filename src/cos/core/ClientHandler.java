package cos.core;

// Java Imports

import cos.metadata.Constants;
import cos.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Other Imports

public class ClientHandler implements Runnable {

    // Variables
    private final List<GameClient> activeClients = new ArrayList<GameClient>();
    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private long lastTime;
    private float deltaTime;
    // Comparators
    public static final Comparator<ClientHandler> SizeComparator = new Comparator<ClientHandler>() {
        public int compare(ClientHandler o1, ClientHandler o2) {
            return o1.size() - o2.size();
        }
    };

    public ClientHandler(GameClient client) {
        add(client);
    }
    
    public void start() {
        lastTime = System.nanoTime();
        service.scheduleAtFixedRate(this, 0, Constants.TICK_NANOSECOND, TimeUnit.NANOSECONDS);
    }

    public void run() {
        long now = System.nanoTime();
        deltaTime += (now - lastTime) / Constants.TICK_NANOSECOND;
        lastTime = now;

        if (deltaTime >= 1) {
            Iterator<GameClient> it = activeClients.iterator();

            while (it.hasNext()) {
                GameClient client = it.next();

                for (int i = 0; i < 10; i++) {
                    client.run();
                }

                if (!client.isAlive()) {
                    it.remove();
                    GameServer.getInstance().removeActiveClient(client.getID());

                    Log.printf("Client %s has ended", client.getID());
                }
            }

            deltaTime--;
        }

        if (activeClients.isEmpty()) {
            service.shutdown();
            GameServer.getInstance().removeClientHandler(this);
        }
    }

    public final void add(GameClient client) {
        synchronized (activeClients) {
            activeClients.add(client);
        }
    }

    public void remove(GameClient client) {
        synchronized (activeClients) {
            activeClients.remove(client);
        }
    }

    public int size() {
        return activeClients.size();
    }

    public float getDeltaTime() {
        return deltaTime;
    }
}
