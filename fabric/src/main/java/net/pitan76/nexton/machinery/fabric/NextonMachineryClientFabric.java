package net.pitan76.nexton.machinery.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.pitan76.nexton.machinery.client.NextonMachineryClient;

public class NextonMachineryClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        NextonMachineryClient.init();
    }
}
