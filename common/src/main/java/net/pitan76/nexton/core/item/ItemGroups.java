package net.pitan76.nexton.core.item;

import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.nexton.machinery.NextonMachinery;

import static net.pitan76.nexton.core.NextonCore._id;
import static net.pitan76.nexton.core.NextonCore.registry;

public class ItemGroups {

    public static CreativeTabBuilder NI_GROUP = CreativeTabBuilder.create(_id("main"));

    public static void init() {
        if (PlatformUtil.isModLoaded("nextonmachinery"))
            NI_GROUP.setIconM(() -> ItemWrapper.of(NextonMachinery._id("generator")).createStack());

        registry.registerItemGroup(NI_GROUP);
    }
}
