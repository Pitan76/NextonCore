package net.pitan76.nexton.core.api.energy;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;

public class EnergyStorageManager {
    @ExpectPlatform
    public static boolean isLoadedTeamRebornEnergy() {
        return false;
    }

    @ExpectPlatform
    public static void registerEnergyStorage() {

    }

    @ExpectPlatform
    public static void registerEnergyStorage(RegistryResult<BlockEntityType<?>> blockEntityType) {

    }

    @ExpectPlatform
    public static void registerEnergyStorage(BlockEntityTypeWrapper blockEntityType) {

    }

    @ExpectPlatform
    public static void clearEnergyStorage() {

    }

    @ExpectPlatform
    public static void removeEnergyStorage(IEnergyStorage storage) {

    }
}
