package net.pitan76.nexton.machinery.block.base;

import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.api.block.ExtendBlockEntityProvider;
import net.pitan76.mcpitanlib.api.block.args.v2.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.v3.CompatBlock;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.block.StateReplacedEvent;
import net.pitan76.mcpitanlib.api.state.property.BooleanProperty;
import net.pitan76.mcpitanlib.api.state.property.CompatProperties;
import net.pitan76.mcpitanlib.api.state.property.DirectionProperty;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
import net.pitan76.mcpitanlib.midohra.world.World;
import net.pitan76.nexton.machinery.NextonMachinery;
import net.pitan76.nexton.machinery.api.energy.IEnergyStorage;
import net.pitan76.nexton.machinery.api.state.NextonProperties;
import net.pitan76.nexton.machinery.api.state.IActiveHolder;
import net.pitan76.nexton.machinery.block.entity.base.MachineBlockEntityWithExtendedContainer;
import org.jetbrains.annotations.Nullable;

public abstract class MachineBlock extends CompatBlock implements ExtendBlockEntityProvider, IActiveHolder {

    public static DirectionProperty FACING = CompatProperties.HORIZONTAL_FACING;
    public static BooleanProperty ACTIVE = NextonProperties.ACTIVE;
    public static BooleanProperty POWERED = CompatProperties.POWERED;

    public MachineBlock(CompatibleBlockSettings settings) {
        super(settings);
        setDefaultState(getDefaultMidohraState()
                .with(FACING, Direction.NORTH)
                .with(ACTIVE, false)
                .with(POWERED, false));
    }

    public CompatActionResult onRightClick(BlockUseEvent e) {
        if (e.isSneaking()) return e.pass();
        if (e.isClient()) return e.success();

        if (!e.hasBlockEntity()) return e.pass();

        BlockEntityWrapper blockEntityWrapper = e.getBlockEntityWrapper();
        if (!blockEntityWrapper.instanceOf(MachineBlockEntityWithExtendedContainer.class)) return e.pass();

        MachineBlockEntityWithExtendedContainer blockEntity =
                blockEntityWrapper.getCompatBlockEntity(MachineBlockEntityWithExtendedContainer.class);

        e.player.openExtendedMenu(blockEntity);
        return e.success();
    }

    @Override
    public void onStateReplaced(StateReplacedEvent e) {
        e.spawnDropsInContainer();
        if (e.hasBlockEntity()) {
            BlockEntity blockEntity = e.getBlockEntity();
            if (blockEntity instanceof IEnergyStorage) {
                NextonMachinery.removeEnergyStorage((IEnergyStorage) blockEntity);
            }
        }

        super.onStateReplaced(e);
    }

    @Override
    public @Nullable BlockState getPlacementState(PlacementStateArgs args) {
        if (args == null) return super.getPlacementState(args);
        return this.getDefaultMidohraState().with(FACING, args.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void appendProperties(AppendPropertiesArgs args) {
        args.addProperty(FACING);
        args.addProperty(ACTIVE);
        args.addProperty(POWERED);
        super.appendProperties(args);
    }

    @Override
    public boolean isActive(BlockState state) {
        return state.get(ACTIVE);
    }

    @Override
    public void setActive(World world, BlockPos pos, boolean active) {
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, state.with(ACTIVE, active));
    }

    public Direction getFacing(BlockState state) {
        return state.get(FACING);
    }

    public boolean isPowered(BlockState state) {
        return state.get(POWERED);
    }
}
