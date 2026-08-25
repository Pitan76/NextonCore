package net.pitan76.nexton.core.api.util;

import net.pitan76.mcpitanlib.api.transfer.energy.v1.EnergyLookup;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
import net.pitan76.mcpitanlib.midohra.world.World;
import net.pitan76.nexton.core.api.energy.EnergyStorageBridge;
import net.pitan76.nexton.core.api.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

public class EnergyUtil {
    public static long transfer(IEnergyStorage from, IEnergyStorage to, long maxAmount) {
        if (!canTransfer(from, to)) return 0;

        long amount = Math.min(maxAmount, to.getUsableCapacity());
        long extracted = from.extractEnergy(amount, true, false);
        long inserted = to.insertEnergy(extracted, true, false);

        if (extracted != inserted) return 0;

        from.extractEnergy(extracted, false, false);
        to.insertEnergy(inserted, false, false);

        return inserted;
    }

    public static long transfer(IEnergyStorage from, IEnergyStorage to) {
        return transfer(from, to, from.getMaxOutputEnergy());
    }

    public static boolean canTransfer(IEnergyStorage from, IEnergyStorage to) {
        return from.canExtractEnergy() && to.canInsertEnergy() && from.getEnergyStored() > 0 && to.getUsableCapacity() > 0;
    }

    @Deprecated
    public static boolean transfer(BlockEntityWrapper from, BlockEntityWrapper to) {
        if (!from.instanceOf(IEnergyStorage.class) || !to.instanceOf(IEnergyStorage.class)) return false;
        return transfer((IEnergyStorage) from.get(), (IEnergyStorage) to.get()) > 0;
    }

    public static long transfer(BlockEntityWrapper from, BlockEntityWrapper to, long maxAmount) {
        IEnergyStorage fromStorage = getEnergyStorage(from);
        IEnergyStorage toStorage = getEnergyStorage(to);
        if (fromStorage == null || toStorage == null) return 0;

        return transfer(fromStorage, toStorage, maxAmount);
    }

    @Deprecated
    public static boolean canTransfer(BlockEntityWrapper from, BlockEntityWrapper to) {
        if (!from.instanceOf(IEnergyStorage.class) || !to.instanceOf(IEnergyStorage.class)) return false;
        return canTransfer((IEnergyStorage) from.get(), (IEnergyStorage) to.get());
    }

    public static boolean canTransfer(BlockEntityWrapper from, BlockEntityWrapper to, long maxAmount) {
        IEnergyStorage fromStorage = getEnergyStorage(from);
        IEnergyStorage toStorage = getEnergyStorage(to);
        if (fromStorage == null || toStorage == null) return false;

        return canTransfer(fromStorage, toStorage);
    }

    public static boolean transferNearby(BlockEntityWrapper from, long maxAmount) {
        World world = from.getWorld();
        BlockPos pos = from.getPos();

        if (world.isNull()) return false;

        IEnergyStorage fromStorage = getEnergyStorage(from);
        if (fromStorage == null) return false;

        for (Direction dir : Direction.values()) {
            BlockPos nearPos = pos.offset(dir);
            if (!world.hasBlockEntity(nearPos)) continue;

            BlockEntityWrapper to = world.getBlockEntity(nearPos);
            if (to.isEmpty()) continue;

            IEnergyStorage toStorage = getEnergyStorage(to, dir.getOpposite());
            if (toStorage == null) continue;

            if (canTransfer(fromStorage, toStorage)) {
                transfer(fromStorage, toStorage, maxAmount);
                return true;
            }
        }

        return false;
    }

    public static boolean isEnergyStorage(BlockEntityWrapper blockEntity) {
        if (blockEntity.isEmpty()) return false;
        return isEnergyStorage(blockEntity);
    }

    public static boolean isEnergyStorage(World world, BlockPos pos, @Nullable Direction side) {
        return getEnergyStorage(world, pos, side) != null;
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(BlockEntityWrapper blockEntity, @Nullable Direction side) {
        if (blockEntity == null) return null;
        if (blockEntity.instanceOf(IEnergyStorage.class)) return (IEnergyStorage) blockEntity.get();

        return EnergyStorageBridge.fromMPLorNull(EnergyLookup.ENERGY.find(blockEntity, side));
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(BlockEntityWrapper blockEntity) {
        if (blockEntity.isEmpty()) return null;
        return getEnergyStorage(blockEntity, null);
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(World world, BlockPos pos, @Nullable Direction side) {
        BlockEntityWrapper blockEntity = world.getBlockEntity(pos);
        if (blockEntity.isEmpty()) return null;
        if (blockEntity.instanceOf(IEnergyStorage.class)) return (IEnergyStorage) blockEntity.get();

        return EnergyStorageBridge.fromMPLorNull(EnergyLookup.ENERGY.find(world, pos, side));
    }
}
