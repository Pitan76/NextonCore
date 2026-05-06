package net.pitan76.nexton.core.api.energy.fabric;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.nexton.core.api.energy.IEnergyStorage;
import net.pitan76.nexton.core.fabric.compat.RebornEnergyRegister;

public class EnergyStorageManagerImpl {

    public static void registerEnergyStorage() {
        if (isLoadedTeamRebornEnergy()) {
            RebornEnergyRegister.init();
        }
    }

    public static void clearEnergyStorage() {
        if (isLoadedTeamRebornEnergy()) {
            RebornEnergyRegister.clearEnergyStorage();
        }
    }

    public static void removeEnergyStorage(IEnergyStorage storage) {
        if (isLoadedTeamRebornEnergy()) {
            RebornEnergyRegister.removeEnergyStorage(storage);
        }
    }

    public static void registerEnergyStorage(RegistryResult<BlockEntityType<?>> blockEntityType) {
        if (isLoadedTeamRebornEnergy()) {
            RebornEnergyRegister.registerEnergyStorage(blockEntityType);
        }
    }

    public static void registerEnergyStorage(BlockEntityTypeWrapper blockEntityType) {
        if (isLoadedTeamRebornEnergy()) {
            RebornEnergyRegister.registerEnergyStorage(blockEntityType);
        }
    }

    public static boolean isLoadedTeamRebornEnergy() {
        return PlatformUtil.isModLoaded("team_reborn_energy");
    }
}
