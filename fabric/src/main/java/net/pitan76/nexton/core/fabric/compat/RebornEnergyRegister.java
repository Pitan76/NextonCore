package net.pitan76.nexton.core.fabric.compat;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.lookup.block.BlockApiLookupWithDirection;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.nexton.core.NextonCore;
import net.pitan76.nexton.core.api.energy.IEnergyStorage;
import team.reborn.energy.api.EnergyStorage;

import java.util.HashMap;
import java.util.Map;

public class RebornEnergyRegister {

    private static final Map<IEnergyStorage, TREnergyStorage> energyStorageMap = new HashMap<>();

    public static BlockApiLookupWithDirection<EnergyStorage> SIDED = BlockApiLookupWithDirection.ofDir(EnergyStorage.SIDED);

    public static boolean isInitialized = false;

    public static void init() {
        if (isInitialized) return;
        isInitialized = true;

        System.out.println("[NextonMachinery] Team Reborn Energy detected, registering energy storage...");

        NextonCore.isUsingRebornEnergy = true;

//        List<RegistryResult<BlockEntityType<?>>> tileTypes = BlockEntities.getBookingEnergyStorageBlockEntity();
//        for (RegistryResult<BlockEntityType<?>> result : tileTypes) {
//            EnergyStorage.SIDED.registerForBlockEntity((blockEntity, dir) -> {
//                if (!(blockEntity instanceof IEnergyStorage)) return null;
//                IEnergyStorage storage = (IEnergyStorage) blockEntity;
//
//                return getEnergyStorage(storage);
//            }, result.getOrNull());
//        }
    }

    public static void registerEnergyStorage(RegistryResult<BlockEntityType<?>> blockEntityType) {
        SIDED.registerForBlockEntityM((blockEntity, dir) -> {
            if (!(blockEntity instanceof IEnergyStorage)) return null;
            IEnergyStorage storage = (IEnergyStorage) blockEntity;

            return getEnergyStorage(storage);
        }, blockEntityType.getOrNull());
    }

    public static void registerEnergyStorage(BlockEntityTypeWrapper blockEntityType) {
        SIDED.registerForBlockEntityWrapperM((blockEntity, dir) -> {
            if (!(blockEntity instanceof IEnergyStorage)) return null;
            IEnergyStorage storage = (IEnergyStorage) blockEntity;

            return getEnergyStorage(storage);
        }, blockEntityType);
    }

    public static TREnergyStorage getEnergyStorage(IEnergyStorage storage) {
        if (!energyStorageMap.containsKey(storage)) {
            TREnergyStorage trStorage = new TREnergyStorage(storage);
            energyStorageMap.put(storage, trStorage);
            return trStorage;
        }

        return energyStorageMap.get(storage);
    }

    public static void removeEnergyStorage(IEnergyStorage storage) {
        energyStorageMap.remove(storage);
    }

    public static void clearEnergyStorage() {
        energyStorageMap.clear();
    }
}