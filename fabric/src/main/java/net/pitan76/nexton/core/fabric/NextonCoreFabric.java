package net.pitan76.nexton.core.fabric;

import net.pitan76.nexton.core.NextonCore;
import net.fabricmc.api.ModInitializer;

public class NextonCoreFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        new NextonCore();
    }
}