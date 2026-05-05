package net.pitan76.nexton.machinery.api.state;

import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
import net.pitan76.nexton.machinery.block.base.MachineBlock;

public interface ICompatBlockEntityMachine extends ICompatBlockEntityActiveHolder {
    default Direction getFacing() {
        CompatBlockEntity blockEntity = (CompatBlockEntity) this;
        BlockState state = blockEntity.getMidohraCachedState();
        if (state.contains(MachineBlock.FACING))
            return state.get(MachineBlock.FACING);

        return Direction.NORTH;
    }

    default boolean isPowered() {
        CompatBlockEntity blockEntity = (CompatBlockEntity) this;
        BlockState state = blockEntity.getMidohraCachedState();
        if (state.contains(MachineBlock.POWERED))
            return state.get(MachineBlock.POWERED);

        return false;
    }
}
