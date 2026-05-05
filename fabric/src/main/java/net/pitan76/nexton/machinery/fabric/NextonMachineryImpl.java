package net.pitan76.nexton.machinery.fabric;

import net.pitan76.nexton.machinery.api.energy.IEnergyStorage;
import net.pitan76.nexton.machinery.fabric.compat.RebornEnergyRegister;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;

public class NextonMachineryImpl {
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

    public static boolean isLoadedTeamRebornEnergy() {
        return PlatformUtil.isModLoaded("team_reborn_energy");
    }
}
