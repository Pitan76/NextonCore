package net.pitan76.nexton.machinery.fabric;

import net.pitan76.nexton.machinery.NextonMachinery;
import net.fabricmc.api.ModInitializer;
import net.pitan76.nexton.machinery.fabric.compat.RebornEnergyRegister;

import static net.pitan76.nexton.machinery.NextonMachinery.isLoadedTeamRebornEnergy;

public class NextonMachineryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        new NextonMachinery();

        // BlockEntityType登録後にEnergyStorageを登録しないといけない。allRegister()が終わった後に登録する
        if (isLoadedTeamRebornEnergy()) {
            RebornEnergyRegister.init();
        }
    }
}