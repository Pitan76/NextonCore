package net.pitan76.nexton.core.api.energy;

import org.jetbrains.annotations.Nullable;

/**
 * Nexton の {@link IEnergyStorage} と MCPitanLib のエネルギーストレージを相互変換する。
 * <p>
 * MCPitanLib 側は Fabric の Team Reborn Energy / NeoForge の Capability へ橋渡しされるため、
 * 他MODの機械もこのブリッジを通せば Nexton の機械と同じように扱える。
 */
public class EnergyStorageBridge {

    private EnergyStorageBridge() {
    }

    /**
     * Nexton のストレージを MCPitanLib のストレージへ変換する。
     */
    public static net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage toMPL(IEnergyStorage storage) {
        return new net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage() {
            @Override
            public long getAmount() {
                return storage.getEnergyStored();
            }

            @Override
            public long getCapacity() {
                return storage.getCapacityEnergy();
            }

            @Override
            public long insert(long maxAmount, boolean simulate) {
                return storage.insertEnergy(maxAmount, simulate, true);
            }

            @Override
            public long extract(long maxAmount, boolean simulate) {
                return storage.extractEnergy(maxAmount, simulate, true);
            }

            @Override
            public boolean supportsInsertion() {
                return storage.canInsertEnergy();
            }

            @Override
            public boolean supportsExtraction() {
                return storage.canExtractEnergy();
            }
        };
    }

    @Nullable
    public static net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage toMPL(@Nullable Object storage) {
        if (storage instanceof IEnergyStorage) return toMPL((IEnergyStorage) storage);
        return null;
    }

    /**
     * MCPitanLib のストレージを Nexton のストレージへ変換する
     */
    public static IEnergyStorage fromMPL(net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage storage) {
        return new IEnergyStorage() {
            @Override
            public long getEnergyStored() {
                return storage.getAmount();
            }

            @Override
            public void setEnergyStored(long energy) {
                // 他MODのストレージには残量を直接設定する手段が無い
                throw new UnsupportedOperationException("Cannot set energy directly on a foreign energy storage");
            }

            @Override
            public long getCapacityEnergy() {
                return storage.getCapacity();
            }

            @Override
            public long getMaxInputEnergy() {
                return Long.MAX_VALUE;
            }

            @Override
            public long getMaxOutputEnergy() {
                return Long.MAX_VALUE;
            }

            @Override
            public boolean canExtractEnergy() {
                return storage.supportsExtraction();
            }

            @Override
            public boolean canInsertEnergy() {
                return storage.supportsInsertion();
            }

            @Override
            public long insertEnergy(long amount, boolean simulate, boolean check) {
                return storage.insert(amount, simulate);
            }

            @Override
            public long extractEnergy(long amount, boolean simulate, boolean check) {
                return storage.extract(amount, simulate);
            }

            @Override
            public void addEnergyStored(long amount, boolean check) {
                if (amount >= 0) {
                    storage.insert(amount, false);
                } else {
                    storage.extract(-amount, false);
                }
            }

            @Override
            public long removeEnergyStored(long amount, boolean check) {
                return storage.extract(amount, false);
            }

            @Override
            public boolean canInsertEnergy(long amount) {
                return storage.canInsert(amount);
            }

            @Override
            public boolean canExtractEnergy(long amount) {
                return storage.canExtract(amount);
            }

            @Override
            public long getUsableCapacity() {
                return storage.getSpace();
            }
        };
    }

    @Nullable
    public static IEnergyStorage fromMPLorNull(@Nullable net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage storage) {
        return storage == null ? null : fromMPL(storage);
    }
}
