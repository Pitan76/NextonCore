package net.pitan76.nexton.machinery.block;

import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;
import net.pitan76.nexton.machinery.block.base.MachineBlock;
import net.pitan76.nexton.machinery.block.entity.ElectricFurnaceBlockEntity;

public class ElectricFurnaceBlock extends MachineBlock {

    public static final CompatMapCodec<ElectricFurnaceBlock> CODEC = CompatMapCodec.createCodecOfCompatBlock(ElectricFurnaceBlock::new);

    public ElectricFurnaceBlock(CompatibleBlockSettings settings) {
        super(settings);
    }

    @Override
    public CompatMapCodec<? extends ElectricFurnaceBlock> getCompatCodec() {
        return CODEC;
    }

    @Override
    public BlockEntity createBlockEntity(TileCreateEvent e) {
        return new ElectricFurnaceBlockEntity(e);
    }

    @Override
    public boolean isTick() {
        return true;
    }

}
