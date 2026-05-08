package net.pitan76.nexton.core.fabric.compat;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.pitan76.nexton.core.Config;
import net.pitan76.nexton.core.api.energy.IEnergyStorage;
import team.reborn.energy.api.EnergyStorage;

public class TREnergyStorageWrapper implements IEnergyStorage {

    public static final double CONVERSION_RATE = Config.reborn_energy_conversion_rate;

    private final EnergyStorage storage;

    public TREnergyStorageWrapper(EnergyStorage storage) {
        this.storage = storage;
    }

    public static IEnergyStorage of(EnergyStorage energyStorage) {
        return new TREnergyStorageWrapper(energyStorage);
    }

    public EnergyStorage getStorage() {
        return storage;
    }

    @Override
    public long getCapacityEnergy() {
        return (long) (getStorage().getCapacity() * CONVERSION_RATE);
    }

    @Override
    public long getEnergyStored() {
        return (long) (getStorage().getAmount() * CONVERSION_RATE);
    }

    @Override
    public void setEnergyStored(long energy) {
        long currentEnergy = getEnergyStored();
        long delta = energy - currentEnergy;

        if (delta > 0) {
            try (Transaction transaction = Transaction.openOuter()) {
                getStorage().insert((long) (delta / CONVERSION_RATE), transaction);
                transaction.commit();
            }
        } else if (delta < 0) {
            try (Transaction transaction = Transaction.openOuter()) {
                getStorage().extract((long) (-delta / CONVERSION_RATE), transaction);
                transaction.commit();
            }
        }
    }

    @Override
    public long getMaxInputEnergy() {
        return (long) (canInsertEnergy() ? getStorage().getCapacity() * CONVERSION_RATE : 0);
    }

    @Override
    public long getMaxOutputEnergy() {
        return (long) (canExtractEnergy() ? getStorage().getCapacity() * CONVERSION_RATE : 0);
    }

    @Override
    public long getUsableCapacity() {
        return (long) (getStorage().getCapacity() * CONVERSION_RATE);
    }

    @Override
    public boolean canInsertEnergy() {
        return getStorage().supportsInsertion();
    }

    @Override
    public boolean canExtractEnergy() {
        return getStorage().supportsExtraction();
    }

    @Override
    public boolean canExtractEnergy(long amount) {
        return canExtractEnergy() && getEnergyStored() >= amount;
    }

    @Override
    public boolean canInsertEnergy(long amount) {
        return canInsertEnergy() && getUsableCapacity() >= amount;
    }
}