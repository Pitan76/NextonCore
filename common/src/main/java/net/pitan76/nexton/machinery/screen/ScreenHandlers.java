package net.pitan76.nexton.machinery.screen;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;
import net.pitan76.mcpitanlib.api.registry.result.SupplierResult;

import static net.pitan76.nexton.machinery.NextonMachinery._id;
import static net.pitan76.nexton.machinery.NextonMachinery.registry;

public class ScreenHandlers {
    public static SupplierResult<ScreenHandlerType<FuelGeneratorScreenHandler>> FUEL_GENERATOR_SCREEN_HANDLER;
    public static SupplierResult<ScreenHandlerType<ElectricFurnaceScreenHandler>> ELECTRIC_FURNACE_SCREEN_HANDLER;

    public static void init() {
        FUEL_GENERATOR_SCREEN_HANDLER = register("fuel_generator", FuelGeneratorScreenHandler::new);
        ELECTRIC_FURNACE_SCREEN_HANDLER = register("electric_furnace", ElectricFurnaceScreenHandler::new);
    }

    public static <T extends ScreenHandler> SupplierResult<ScreenHandlerType<T>> register(String id, ExtendedScreenHandlerTypeBuilder.Factory2<T> factory) {
        return registry.registerScreenHandlerType(_id(id), new ExtendedScreenHandlerTypeBuilder<>(factory));
    }
}
