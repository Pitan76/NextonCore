package net.pitan76.nexton.machinery;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.pitan76.nexton.machinery.api.energy.IEnergyStorage;
import net.pitan76.nexton.machinery.block.Blocks;
import net.pitan76.nexton.machinery.block.entity.BlockEntities;
import net.pitan76.nexton.machinery.item.ItemGroups;
import net.pitan76.nexton.machinery.item.Items;
import net.pitan76.nexton.machinery.screen.ScreenHandlers;
import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class NextonMachinery extends CommonModInitializer {
    public static final String MOD_ID = "nextonmachinery";
    public static final String MOD_NAME = "Nexton Machinery";
    public static final String MOD_NAMESPACE = "nexton";

    public static NextonMachinery INSTANCE;
    public static CompatRegistryV2 registry;

    @Override
    public void init() {
        INSTANCE = this;
        registry = super.registry;

        Config.init();

        ItemGroups.init();
        Blocks.init();
        Items.init();
        BlockEntities.init();
        ScreenHandlers.init();

        EventRegistry.ServerLifecycle.serverStopped((server) -> {
            clearEnergyStorage();
        });
    }
    
    @ExpectPlatform
    public static boolean isLoadedTeamRebornEnergy() {
        return false;
    }

    @ExpectPlatform
    public static void registerEnergyStorage() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void clearEnergyStorage() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void removeEnergyStorage(IEnergyStorage storage) {
        throw new AssertionError();
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