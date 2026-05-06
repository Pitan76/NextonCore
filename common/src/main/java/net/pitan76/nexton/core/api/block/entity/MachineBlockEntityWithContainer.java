package net.pitan76.nexton.core.api.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.guilib.api.block.entity.BlockEntityWithContainer;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.nexton.core.api.energy.IEnergyStorage;
import net.pitan76.nexton.core.api.state.ICompatBlockEntityMachine;

public abstract class MachineBlockEntityWithContainer extends BlockEntityWithContainer implements IEnergyStorage, ICompatBlockEntityMachine {
    public MachineBlockEntityWithContainer(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public MachineBlockEntityWithContainer(BlockEntityTypeWrapper type, TileCreateEvent e) {
        super(type.get(), e);
    }

    @Override
    public void writeNbt(WriteNbtArgs args) {
        super.writeNbt(args);
        writeEnergyNbt(args);
    }

    @Override
    public void readNbt(ReadNbtArgs args) {
        super.readNbt(args);
        readEnergyNbt(args);
    }
}
