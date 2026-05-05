package net.pitan76.nexton.machinery.item;

import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.item.v2.ItemSettingsBuilder;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;
import net.pitan76.nexton.machinery.block.Blocks;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import static net.pitan76.nexton.machinery.NextonMachinery._id;
import static net.pitan76.nexton.machinery.NextonMachinery.registry;

public class Items {

    public static final ItemSettingsBuilder STANDARD_ITEM_SETTINGS = ItemSettingsBuilder.of()
            .addGroup(ItemGroups.NI_GROUP);

    public static RegistryResult<Item> FUEL_GENERATOR;
    public static RegistryResult<Item> ELECTRIC_FURNACE;

    public static void init() {
        FUEL_GENERATOR = registry.registerItem(_id("generator"), () -> ItemUtil.create(Blocks.FUEL_GENERATOR.getOrNull(), STANDARD_ITEM_SETTINGS.build(_id("generator"))));
        ELECTRIC_FURNACE = registry.registerItem(_id("electric_furnace"), () -> ItemUtil.create(Blocks.ELECTRIC_FURNACE.getOrNull(), STANDARD_ITEM_SETTINGS.build(_id("electric_furnace"))));
    }
}
