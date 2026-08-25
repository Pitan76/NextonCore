package net.pitan76.nexton.core.api.energy;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.EnergyLookup;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;

public class EnergyStorageManager {
    public static boolean isSupported() {
        return EnergyLookup.ENERGY.isSupported();
    }

    public static void registerEnergyStorage(RegistryResult<BlockEntityType<?>> blockEntityType) {
        EnergyLookup.ENERGY.registerForBlockEntity((blockEntity, direction) ->
                EnergyStorageBridge.toMPL(blockEntity), blockEntityType.get());
    }

    public static void registerEnergyStorage(BlockEntityTypeWrapper blockEntityType) {
        EnergyLookup.ENERGY.registerForBlockEntityWrapper((blockEntity, direction) ->
                EnergyStorageBridge.toMPL(blockEntity.get()), blockEntityType);
    }
}
