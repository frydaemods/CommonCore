package dev.frydae.beguild.events;

import com.google.common.collect.Sets;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.DesertVillageData;
import net.minecraft.structure.PlainsVillageData;
import net.minecraft.structure.SavannaVillageData;
import net.minecraft.structure.SnowyVillageData;
import net.minecraft.structure.TaigaVillageData;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Arrays;
import java.util.HashSet;

public final class StructureGenerationEvents {
    public static Event<PreVillageGeneratedCallback> PRE_VILLAGE_GENERATION = new Event<>(PreVillageGeneratedCallback.class, (listeners) -> (type, world, pos, structure) -> {
        for (PreVillageGeneratedCallback listener : listeners) {
            ActionResult actionResult = listener.onPreVillageGenerate(type, world, pos, structure);

            if (actionResult != ActionResult.PASS) {
                return ActionResult.FAIL;
            }
        }

        return ActionResult.PASS;
    });

    public static Event<PostVillageGeneratedCallback> POST_VILLAGE_GENERATION = new Event<>(PostVillageGeneratedCallback.class, (listeners) -> (type, world, pos, structure) -> {
        for (PostVillageGeneratedCallback listener : listeners) {
            listener.onPostVillageGenerate(type, world, pos, structure);
        }
    });

    public static Event<PreBastionGeneratedCallback> PRE_BASTION_GENERATION = new Event<>(PreBastionGeneratedCallback.class, (listeners) -> (world, pos, structure) -> {
        for (PreBastionGeneratedCallback listener : listeners) {
            ActionResult actionResult = listener.onPreBastionGenerate(world, pos, structure);

            if (actionResult != ActionResult.PASS) {
                return ActionResult.FAIL;
            }
        }

        return ActionResult.PASS;
    });

    public static Event<PostBastionGeneratedCallback> POST_BASTION_GENERATION = new Event<>(PostBastionGeneratedCallback.class, (listeners) -> (world, pos, structure) -> {
        for (PostBastionGeneratedCallback listener : listeners) {
            listener.onPostBastionGenerate(world, pos, structure);
        }
    });

    public static Event<PreAncientCityGeneratedCallback> PRE_ANCIENT_CITY_GENERATION = new Event<>(PreAncientCityGeneratedCallback.class, (listeners) -> (world, pos, structure) -> {
        for (PreAncientCityGeneratedCallback listener : listeners) {
            ActionResult actionResult = listener.onPreAncientCityGeneration(world, pos, structure);

            if (actionResult != ActionResult.PASS) {
                return ActionResult.FAIL;
            }
        }

        return ActionResult.PASS;
    });

    public static Event<PostAncientCityGeneratedCallback> POST_ANCIENT_CITY_GENERATION = new Event<>(PostAncientCityGeneratedCallback.class, (listeners) -> (world, pos, structure) -> {
        for (PostAncientCityGeneratedCallback listener : listeners) {
            listener.onPostAncientCityGenerate(world, pos, structure);
        }
    });

    public static Event<PreStructureGeneratedCallback> PRE_STRUCTURE_GENERATION = new Event<>(PreStructureGeneratedCallback.class, (listeners) -> (type, world, pos, structure) -> {
        for (PreStructureGeneratedCallback listener : listeners) {
            ActionResult actionResult = listener.onPreStructureGeneration(type, world, pos, structure);

            if (actionResult != ActionResult.PASS) {
                return ActionResult.FAIL;
            }
        }

        return ActionResult.PASS;
    });

    public static Event<PostStructureGeneratedCallback> POST_STRUCTURE_GENERATION = new Event<>(PostStructureGeneratedCallback.class, (listeners) -> (type, world, pos, structure) -> {
        for (PostStructureGeneratedCallback listener : listeners) {
            listener.onPostStructureGenerate(type, world, pos, structure);
        }
    });

    @FunctionalInterface
    public interface PreVillageGeneratedCallback {
        ActionResult onPreVillageGenerate(VillageType type, ServerWorld world, ChunkPos pos, Structure structure);
    }

    @FunctionalInterface
    public interface PostVillageGeneratedCallback {
        void onPostVillageGenerate(VillageType type, ServerWorld world, ChunkPos pos, Structure structure);
    }

    public interface PreBastionGeneratedCallback {
        ActionResult onPreBastionGenerate(ServerWorld world, ChunkPos pos, Structure structure);
    }

    public interface PostBastionGeneratedCallback {
        void onPostBastionGenerate(ServerWorld world, ChunkPos pos, Structure structure);
    }

    public interface PreAncientCityGeneratedCallback {
        ActionResult onPreAncientCityGeneration(ServerWorld world, ChunkPos pos, Structure structure);
    }

    public interface PostAncientCityGeneratedCallback {
        void onPostAncientCityGenerate(ServerWorld world, ChunkPos pos, Structure structure);
    }

    @FunctionalInterface
    public interface PreStructureGeneratedCallback {
        ActionResult onPreStructureGeneration(StructureStartType type, ServerWorld world, ChunkPos pos, Structure structure);
    }

    @FunctionalInterface
    public interface PostStructureGeneratedCallback {
        void onPostStructureGenerate(StructureStartType type, ServerWorld world, ChunkPos pos, Structure structure);
    }

    public enum StructureStartType {
        JIGSAW(StructureType.JIGSAW),
        RUINED_PORTAL(StructureType.RUINED_PORTAL),
        END_CITY(StructureType.END_CITY),
        STRONGHOLD(StructureType.STRONGHOLD),
        MINESHAFT(StructureType.MINESHAFT),
        BURIED_TREASURE(StructureType.BURIED_TREASURE),
        OCEAN_RUIN(StructureType.OCEAN_RUIN),
        SHIPWRECK(StructureType.SHIPWRECK),
        OCEAN_MONUMENT(StructureType.OCEAN_MONUMENT),
        IGLOO(StructureType.IGLOO),
        SWAMP_HUT(StructureType.SWAMP_HUT),
        DESERT_PYRAMID(StructureType.DESERT_PYRAMID),
        JUNGLE_TEMPLE(StructureType.JUNGLE_TEMPLE),
        WOODLAND_MANSION(StructureType.WOODLAND_MANSION),
        FORTRESS(StructureType.FORTRESS),
        NETHER_FOSSIL(StructureType.NETHER_FOSSIL);

        private final StructureType<?> type;

        StructureStartType(StructureType<?> type) {
            this.type = type;
        }

        public static StructureStartType getByType(StructureType<?> type) {
            return Arrays.stream(values()).filter(value -> value.type.equals(type)).findFirst().orElse(null);

        }
    }

    public enum VillageType {
        DESERT(DesertVillageData.TOWN_CENTERS_KEY),
        PLAINS(PlainsVillageData.TOWN_CENTERS_KEY),
        SAVANNA(SavannaVillageData.TOWN_CENTERS_KEY),
        SNOWY(SnowyVillageData.TOWN_CENTERS_KEY),
        TAIGA(TaigaVillageData.TOWN_CENTERS_KEY);

        private final RegistryKey<StructurePool> townCentersKey;

        VillageType(RegistryKey<StructurePool> townCentersKey) {
            this.townCentersKey = townCentersKey;
        }

        public static HashSet<Object> keySet() {
            return Sets.newHashSet(Arrays.stream(values()).map(value -> value.townCentersKey).toArray());
        }

        public static VillageType getByKey(RegistryKey<StructurePool> key) {
            for (VillageType type : values()) {
                if (type.townCentersKey.equals(key)) {
                    return type;
                }
            }

            return null;
        }
    }
}
