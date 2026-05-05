package net.pitan76.nexton.core.fabric;

import net.pitan76.nexton.core.client.NextonCoreClient;
import net.fabricmc.api.ClientModInitializer;

public class NextonCoreClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        NextonCoreClient.init();
    }
}
