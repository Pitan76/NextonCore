package net.pitan76.nexton.core;

import net.pitan76.nexton.core.item.ItemGroups;
import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class NextonCore extends CommonModInitializer {
    public static final String MOD_ID = "nextoncore";
    public static final String MOD_NAME = "Nexton Core";
    public static final String MOD_NAMESPACE = "nexton";

    public static NextonCore INSTANCE;
    public static CompatRegistryV2 registry;

    public static boolean isUsingRebornEnergy = false;

    public NextonCore() {
        super();
    }

    @Override
    public void init() {
        INSTANCE = this;
        registry = super.registry;

        ItemGroups.init();
    }

    // ----

    @Override
    public String getId() {
        return MOD_ID;
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }

    public static CompatIdentifier _id(String path) {
        return CompatIdentifier.of(MOD_NAMESPACE, path);
    }
}