package net.pitan76.nexton.machinery.api.state;

public interface IBlockEntityActiveHolder extends IActiveHolder {
    void setActive(boolean active);

    boolean isActive();
}
