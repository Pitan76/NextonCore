package net.pitan76.nexton.core.item;

import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;

import static net.pitan76.nexton.core.NextonCore._id;
import static net.pitan76.nexton.core.NextonCore.registry;

public class ItemGroups {

    public static CreativeTabBuilder NI_GROUP = CreativeTabBuilder.create(_id("main"));

    public static void init() {
        registry.registerItemGroup(NI_GROUP);
    }
}
