package net.pitan76.nexton.core.api.util;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.EnergyLookup;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.api.util.math.PosUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
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
    public static boolean transfer(BlockEntity from, BlockEntity to) {
        if (!(from instanceof IEnergyStorage) || !(to instanceof IEnergyStorage)) return false;
        return transfer((IEnergyStorage) from, (IEnergyStorage) to) > 0;
    }

    public static long transfer(BlockEntity from, BlockEntity to, long maxAmount) {
        IEnergyStorage fromStorage = getEnergyStorage(from);
        IEnergyStorage toStorage = getEnergyStorage(to);
        if (fromStorage == null || toStorage == null) return 0;

        return transfer(fromStorage, toStorage, maxAmount);
    }

    @Deprecated
    public static boolean canTransfer(BlockEntity from, BlockEntity to) {
        if (!(from instanceof IEnergyStorage) || !(to instanceof IEnergyStorage)) return false;
        return canTransfer((IEnergyStorage) from, (IEnergyStorage) to);
    }

    public static boolean canTransfer(BlockEntity from, BlockEntity to, long maxAmount) {
        IEnergyStorage fromStorage = getEnergyStorage(from);
        IEnergyStorage toStorage = getEnergyStorage(to);
        if (fromStorage == null || toStorage == null) return false;

        return canTransfer(fromStorage, toStorage);
    }

    public static boolean transferNearby(BlockEntity from, long maxAmount) {
        World world = from.getWorld();
        BlockPos pos = from.getPos();

        if (world == null) return false;

        BlockPos[] nearPositions = PosUtil.getNeighborPoses(pos);
        for (BlockPos nearPos : nearPositions) {
            if (!WorldUtil.hasBlockEntity(world, nearPos)) continue;

            BlockEntity to = WorldUtil.getBlockEntity(world, nearPos);
            if (to == null) continue;

            if (canTransfer(from, to, maxAmount)) {
                transfer(from, to, maxAmount);
                return true;
            }
        }

        return false;
    }

    public static boolean isEnergyStorage(BlockEntity blockEntity) {
        return getEnergyStorage(blockEntity) != null;
    }

    public static boolean isEnergyStorage(BlockEntityWrapper blockEntity) {
        if (blockEntity.isEmpty()) return false;
        return isEnergyStorage(blockEntity.get());
    }

    public static boolean isEnergyStorage(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, @Nullable Direction side) {
        return getEnergyStorage(world, pos, side) != null;
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(BlockEntity blockEntity) {
        if (blockEntity == null) return null;
        if (blockEntity instanceof IEnergyStorage) return (IEnergyStorage) blockEntity;

        return EnergyStorageBridge.fromMPLorNull(EnergyLookup.ENERGY.find(blockEntity, null));
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(BlockEntityWrapper blockEntity) {
        if (blockEntity.isEmpty()) return null;
        return getEnergyStorage(blockEntity.get());
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, @Nullable Direction side) {
        BlockEntityWrapper blockEntity = world.getBlockEntity(pos);
        if (blockEntity.isEmpty()) return null;
        if (blockEntity.get() instanceof IEnergyStorage) return (IEnergyStorage) blockEntity.get();

        return EnergyStorageBridge.fromMPLorNull(EnergyLookup.ENERGY.find(world, pos, side));
    }
}
