package net.pitan76.nexton.core.api.util.fabric;

import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
import net.pitan76.nexton.core.api.util.EnergyUtil;
import net.pitan76.nexton.core.fabric.compat.RebornEnergyRegister;
import net.pitan76.nexton.core.api.energy.IEnergyStorage;
import net.pitan76.nexton.core.fabric.compat.TREnergyStorageWrapper;
import org.jetbrains.annotations.Nullable;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;

import static net.pitan76.nexton.machinery.NextonMachinery.isLoadedTeamRebornEnergy;

public class EnergyUtilImpl {
    public static boolean canTransferOther(BlockEntity from, BlockEntity to, long maxAmount) {
        if (!isLoadedTeamRebornEnergy()) return false;

        EnergyStorage trFrom = null;
        EnergyStorage trTo = null;

        if (from instanceof IEnergyStorage) trFrom = RebornEnergyRegister.getEnergyStorage((IEnergyStorage) from);
        if (from instanceof PowerAcceptorBlockEntity) trFrom = ((PowerAcceptorBlockEntity) from).getSideEnergyStorage(null);

        if (to instanceof IEnergyStorage) trTo = RebornEnergyRegister.getEnergyStorage((IEnergyStorage) to);
        if (to instanceof PowerAcceptorBlockEntity) trTo = ((PowerAcceptorBlockEntity) to).getSideEnergyStorage(null);

        if (trFrom == null || trTo == null) return false;
        
        return trFrom.supportsExtraction() && trTo.supportsInsertion() && trFrom.getAmount() > 0 && trTo.getCapacity() - trTo.getAmount() > 0;
    }

    public static long transferOther(BlockEntity from, BlockEntity to, long maxAmount) {
        if (!isLoadedTeamRebornEnergy()) return 0;

        EnergyStorage trFrom = null;
        EnergyStorage trTo = null;

        if (from instanceof IEnergyStorage) trFrom = RebornEnergyRegister.getEnergyStorage((IEnergyStorage) from);
        if (from instanceof PowerAcceptorBlockEntity) trFrom = ((PowerAcceptorBlockEntity) from).getSideEnergyStorage(null);

        if (to instanceof IEnergyStorage) trTo = RebornEnergyRegister.getEnergyStorage((IEnergyStorage) to);
        if (to instanceof PowerAcceptorBlockEntity) trTo = ((PowerAcceptorBlockEntity) to).getSideEnergyStorage(null);

        if (trFrom == null || trTo == null) return 0;

        return EnergyStorageUtil.move(
                trFrom,
                trTo,
                maxAmount,
                null
        );
    }

    public static boolean isTeamRebornEnergyStorage(BlockEntity blockEntity) {
        if (!isLoadedTeamRebornEnergy()) return false;

        return blockEntity instanceof PowerAcceptorBlockEntity;
    }

    public static boolean isEnergyStorage(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, @Nullable Direction side) {
        BlockEntityWrapper blockEntity = world.getBlockEntity(pos);
        if (!isLoadedTeamRebornEnergy()) return EnergyUtil.isEnergyStorage(blockEntity);

        if (blockEntity.isEmpty()) return false;
        EnergyStorage energyStorage = RebornEnergyRegister.SIDED.find(world, pos, side);

        return energyStorage != null;
    }

    public static IEnergyStorage getEnergyStorage(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, @Nullable Direction side) {
        BlockEntityWrapper blockEntity = world.getBlockEntity(pos);
        if (blockEntity.isEmpty()) return null;
        if (blockEntity.get() instanceof IEnergyStorage) return (IEnergyStorage) blockEntity.get();

        if (!isLoadedTeamRebornEnergy()) return null;

        EnergyStorage energyStorage = RebornEnergyRegister.SIDED.find(blockEntity.getWorld(), blockEntity.getPos(), side);
        return TREnergyStorageWrapper.of(energyStorage);
    }
}
