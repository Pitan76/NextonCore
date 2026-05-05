package net.pitan76.nexton.machinery.block.entity.base;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.nexton.machinery.api.energy.IEnergyStorage;
import net.pitan76.nexton.machinery.api.state.ICompatBlockEntityMachine;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public abstract class MachineBlockEntity extends CompatBlockEntity implements IEnergyStorage, ICompatBlockEntityMachine {
    public MachineBlockEntity(BlockEntityType<?> type, TileCreateEvent e) {
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
