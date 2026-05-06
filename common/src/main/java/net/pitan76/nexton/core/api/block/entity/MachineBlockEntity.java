package net.pitan76.nexton.core.api.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.nexton.core.api.energy.IEnergyStorage;
import net.pitan76.nexton.core.api.state.ICompatBlockEntityMachine;

public abstract class MachineBlockEntity extends CompatBlockEntity implements IEnergyStorage, ICompatBlockEntityMachine {
    public MachineBlockEntity(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public MachineBlockEntity(BlockEntityTypeWrapper type, TileCreateEvent e) {
        super(type, e);
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
