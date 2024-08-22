package dev.frydae.beguild.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record CustomItemMeta(String key, boolean soulbound, boolean usable, boolean unplaceable, boolean keepOnDeath) {
    public static final Codec<CustomItemMeta> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("key").forGetter(CustomItemMeta::key),
            Codec.BOOL.fieldOf("soulbound").forGetter(CustomItemMeta::soulbound),
            Codec.BOOL.fieldOf("usable").forGetter(CustomItemMeta::usable),
            Codec.BOOL.fieldOf("unplaceable").forGetter(CustomItemMeta::unplaceable),
            Codec.BOOL.fieldOf("keepOnDeath").forGetter(CustomItemMeta::keepOnDeath)
    ).apply(instance, CustomItemMeta::new));

    public static final CustomItemMeta DEFAULT = new CustomItemMeta(null, false, false, false, false);

    public boolean isFlag(String flag) {
        return switch (flag.toLowerCase()) {
            case "soulbound" -> soulbound;
            case "usable" -> usable;
            case "unplaceable" -> unplaceable;
            case "keep_on_death" -> keepOnDeath;
            default -> false;
        };
    }

    public CustomItemMeta withFlag(String flag) {
        return withFlag(flag, true);
    }

    public CustomItemMeta withFlag(String flag, boolean value) {
        return switch (flag.toLowerCase()) {
            case "soulbound" -> withSoulbound(value);
            case "usable" -> withUsable(value);
            case "unplaceable" -> withUnplaceable(value);
            case "keep_on_death" -> withKeepOnDeath(value);
            default -> this;
        };
    }

    public boolean isEmpty() {
        return key == null && !soulbound && !usable && !unplaceable && !keepOnDeath;
    }

    CustomItemMeta withKey(String key) {
        if (key.isEmpty()) {
            key = null;
        }

        return new CustomItemMeta(key, soulbound, usable, unplaceable, keepOnDeath);
    }

    CustomItemMeta withSoulbound(boolean soulbound) {
        return new CustomItemMeta(key, soulbound, usable, unplaceable, keepOnDeath);
    }

    CustomItemMeta withUsable(boolean usable) {
        return new CustomItemMeta(key, soulbound, usable, unplaceable, keepOnDeath);
    }

    CustomItemMeta withUnplaceable(boolean unplaceable) {
        return new CustomItemMeta(key, soulbound, usable, unplaceable, keepOnDeath);
    }

    CustomItemMeta withKeepOnDeath(boolean keepOnDeath) {
        return new CustomItemMeta(key, soulbound, usable, unplaceable, keepOnDeath);
    }
}
