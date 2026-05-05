package net.pitan76.nexton.machinery.api.state;

import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.World;

public interface IActiveHolder {
    default boolean isActive(BlockState state) {
        return state.contains(NextonProperties.ACTIVE) &&
                state.get(NextonProperties.ACTIVE);
    }

    default void setActive(World world, BlockPos pos, boolean active) {
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, state.with(NextonProperties.ACTIVE, active));
    }
}
