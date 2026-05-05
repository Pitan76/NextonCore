package net.pitan76.nexton.machinery.block.entity.base;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.guilib.api.block.entity.ExtendedBlockEntityWithContainer;
import net.pitan76.nexton.machinery.api.energy.IEnergyStorage;
import net.pitan76.nexton.machinery.api.state.ICompatBlockEntityMachine;

public abstract class MachineBlockEntityWithExtendedContainer extends ExtendedBlockEntityWithContainer implements IEnergyStorage, ICompatBlockEntityMachine {
    public MachineBlockEntityWithExtendedContainer(BlockEntityType<?> type, TileCreateEvent e) {
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
