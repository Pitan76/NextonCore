package net.pitan76.nexton.core.api.util;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.block.ExtendBlockEntityProvider;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.v3.CompatBlock;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.tile.TileTickEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.tile.ExtendBlockEntityTicker;

public abstract class BlockWithBlockEntity extends CompatBlock implements ExtendBlockEntityProvider {

    public BlockWithBlockEntity(CompatibleBlockSettings settings) {
        super(settings);
    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> getBlockEntityType() {
        return ExtendBlockEntityProvider.super.getBlockEntityType();
    }

    @Override
    public BlockEntity createBlockEntity(TileCreateEvent e) {
        return new LinkedBlockEntity(getBlockEntityType(), e, this);
    }

    public abstract void tick(TileTickEvent<LinkedBlockEntity> e);

    public static class LinkedBlockEntity extends CompatBlockEntity implements ExtendBlockEntityTicker<LinkedBlockEntity> {

        public final BlockWithBlockEntity block;

        public LinkedBlockEntity(BlockEntityType<?> type, TileCreateEvent e, BlockWithBlockEntity block) {
            super(type, e);
            this.block = block;
        }

        @Override
        public void tick(TileTickEvent<LinkedBlockEntity> e) {
            block.tick(e);
        }
    }

}
