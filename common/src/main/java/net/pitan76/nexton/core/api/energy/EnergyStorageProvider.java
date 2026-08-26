package net.pitan76.nexton.core.api.energy;

import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IMutableEnergyStorage;

public interface EnergyStorageProvider extends IMutableEnergyStorage {
    IEnergyStorage getEnergyStorage();

    @Override
    default long getEnergyStored() {
        return getEnergyStorage().getEnergyStored();
    }

    @Override
    default void setEnergyStored(long energy) {
        getEnergyStorage().setEnergyStored(energy);
    }

    @Override
    default long getCapacityEnergy() {
        return getEnergyStorage().getCapacityEnergy();
    }

    @Override
    default long getMaxInputEnergy() {
        return getEnergyStorage().getMaxInputEnergy();
    }

    @Override
    default long getMaxOutputEnergy() {
        return getEnergyStorage().getMaxOutputEnergy();
    }

    @Override
    default boolean canExtractEnergy() {
        return getEnergyStorage().canExtractEnergy();
    }

    @Override
    default boolean canInsertEnergy() {
        return getEnergyStorage().canInsertEnergy();
    }

    @Override
    default long insertEnergy(long amount, boolean simulate) {
        return getEnergyStorage().insertEnergy(amount, simulate);
    }

    @Override
    default long extractEnergy(long amount, boolean simulate) {
        return getEnergyStorage().extractEnergy(amount, simulate);
    }
}
