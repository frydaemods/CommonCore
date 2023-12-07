package dev.frydae.beguild.mixin;

import dev.frydae.beguild.events.StructureGenerationEvents;
import dev.frydae.beguild.mixin.accessors.JigsawStructureAccessor;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.AncientCityGenerator;
import net.minecraft.structure.BastionRemnantGenerator;
import net.minecraft.structure.StructurePiecesList;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(StructureStart.class)
public class StructureStartMixin {
    @Shadow @Final private Structure structure;
    @Shadow @Final private StructurePiecesList children;
    @Shadow @Final private ChunkPos pos;

    @Inject(method = "place", at = @At(value = "HEAD"), cancellable = true)
    public void beguild$place(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, CallbackInfo ci) {
        ServerWorld serverWorld = world.toServerWorld();

        StructureGenerationEvents.StructureStartType byType = StructureGenerationEvents.StructureStartType.getByType(structure.getType());

        ActionResult result = ActionResult.PASS;

        if (byType != null) {
            if (byType == StructureGenerationEvents.StructureStartType.JIGSAW) {
                RegistryEntry<StructurePool> startJigsawName = ((JigsawStructureAccessor) structure).getStartPool();

                if (startJigsawName != null && startJigsawName.getKey().isPresent()) {
                    RegistryKey<StructurePool> key = startJigsawName.getKey().get();

                    if (StructureGenerationEvents.VillageType.keySet().contains(key)) {
                        StructureGenerationEvents.VillageType type = StructureGenerationEvents.VillageType.getByKey(key);

                        result = StructureGenerationEvents.PRE_VILLAGE_GENERATION.getInvoker().onPreVillageGenerate(type, serverWorld, chunkPos, structure);
                    } else if (key.equals(BastionRemnantGenerator.STRUCTURE_POOLS)) {
                        result = StructureGenerationEvents.PRE_BASTION_GENERATION.getInvoker().onPreBastionGenerate(serverWorld, chunkPos, structure);
                    } else if (key.equals(AncientCityGenerator.CITY_CENTER)) {
                        result = StructureGenerationEvents.PRE_ANCIENT_CITY_GENERATION.getInvoker().onPreAncientCityGeneration(serverWorld, chunkPos, structure);
                    }
                }
            } else {
                result = StructureGenerationEvents.PRE_STRUCTURE_GENERATION.getInvoker().onPreStructureGeneration(byType, serverWorld, chunkPos, structure);
            }
        }

        if (result == ActionResult.FAIL) {
            ci.cancel();
        }
    }
}
