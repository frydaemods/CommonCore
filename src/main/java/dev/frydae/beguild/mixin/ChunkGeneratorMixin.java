package dev.frydae.beguild.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.frydae.beguild.events.StructureGenerationEvents;
import dev.frydae.beguild.mixin.accessors.JigsawStructureAccessor;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.AncientCityGenerator;
import net.minecraft.structure.BastionRemnantGenerator;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {
    @Inject(
            method = "generateFeatures",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/StructureWorldAccess;setCurrentlyGeneratingStructureName(Ljava/util/function/Supplier;)V", ordinal = 2)
    )
    public void beguild$generateFeatures(StructureWorldAccess world, Chunk chunk, StructureAccessor structureAccessor, CallbackInfo ci, @Local(ordinal = 0) ChunkPos chunkPos2) {
        ServerWorld serverWorld = world.toServerWorld();

        chunk.getStructureStarts().forEach((structure, structureStart) -> {
            StructureGenerationEvents.StructureStartType byType = StructureGenerationEvents.StructureStartType.getByType(structureStart.getStructure().getType());

            if (byType != null) {
                if (byType == StructureGenerationEvents.StructureStartType.JIGSAW) {
                    RegistryEntry<StructurePool> startJigsawName = ((JigsawStructureAccessor) structure).getStartPool();

                    if (startJigsawName != null) {
                        startJigsawName.getKey().ifPresent(key -> {
                            if (StructureGenerationEvents.VillageType.keySet().contains(key)) {
                                StructureGenerationEvents.VillageType type = StructureGenerationEvents.VillageType.getByKey(key);

                                StructureGenerationEvents.POST_VILLAGE_GENERATION.getInvoker().onPostVillageGenerate(type, serverWorld, chunkPos2, structure);
                            } else if (key == BastionRemnantGenerator.STRUCTURE_POOLS) {
                                StructureGenerationEvents.POST_BASTION_GENERATION.getInvoker().onPostBastionGenerate(serverWorld, chunkPos2, structure);
                            } else if (key == AncientCityGenerator.CITY_CENTER) {
                                StructureGenerationEvents.POST_ANCIENT_CITY_GENERATION.getInvoker().onPostAncientCityGenerate(serverWorld, chunkPos2, structure);
                            }
                        });
                    }
                } else {
                    StructureGenerationEvents.POST_STRUCTURE_GENERATION.getInvoker().onPostStructureGenerate(byType, serverWorld, chunkPos2, structure);
                }
            }
        });
    }
}
