package net.pitan76.nexton.machinery.client;

import net.pitan76.nexton.machinery.screen.ScreenHandlers;
import net.pitan76.mcpitanlib.guilib.GuiRegistry;

import static net.pitan76.nexton.machinery.NextonMachinery.MOD_ID;

public class NextonMachineryClient {
    public static void init() {
        GuiRegistry.register(MOD_ID, ScreenHandlers.FUEL_GENERATOR_SCREEN_HANDLER.get(), FuelGeneratorScreen::new);
    }
}
