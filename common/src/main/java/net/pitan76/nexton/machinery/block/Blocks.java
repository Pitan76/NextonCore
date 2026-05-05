package net.pitan76.nexton.machinery.block;

import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import static net.pitan76.nexton.machinery.NextonMachinery._id;
import static net.pitan76.nexton.machinery.NextonMachinery.registry;

public class Blocks {

    public static RegistryResult<Block> FUEL_GENERATOR;
    public static RegistryResult<Block> ELECTRIC_FURNACE;

    public static void init() {
        FUEL_GENERATOR = registry.registerBlock(_id("generator"), () -> new FuelGeneratorBlock(CompatibleBlockSettings.of(_id("generator"), CompatibleMaterial.STONE)));
        ELECTRIC_FURNACE = registry.registerBlock(_id("electric_furnace"), () -> new ElectricFurnaceBlock(CompatibleBlockSettings.of(_id("electric_furnace"), CompatibleMaterial.STONE)));
    }
}
