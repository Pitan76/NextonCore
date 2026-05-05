package net.pitan76.nexton.machinery.api.state;

import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public interface ICompatBlockEntityActiveHolder extends IBlockEntityActiveHolder {

    @Override
    default void setActive(boolean active) {
        CompatBlockEntity blockEntity = (CompatBlockEntity) this;
        setActive(blockEntity.getMidohraWorld(), blockEntity.getMidohraPos(), active);
    }

    @Override
    default boolean isActive() {
        CompatBlockEntity blockEntity = (CompatBlockEntity) this;
        return isActive(blockEntity.getMidohraCachedState());
    }
}
