package dev.frydae.beguild.client;

import dev.frydae.beguild.client.types.CustomItemMeta;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BeGuildRegistry {
    public static final ComponentType<CustomItemMeta> CUSTOM_ITEM_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of("beguild", "custom_item"),
            ComponentType.<CustomItemMeta>builder().codec(CustomItemMeta.CODEC).build()
    );

    public static void dummy() {
        // only to load this class
    }
}
