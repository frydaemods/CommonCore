package dev.frydae.commoncore.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import dev.frydae.commoncore.events.ServerPlayerConnectionEvents;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

@Mixin(EntityTrackerEntry.class)
public class EntityTrackerEntryMixin {
    @WrapWithCondition(
            method = "sendPackets",
            at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 0))
    public boolean shouldSendSpawnPacket(Consumer<Packet<ClientPlayPacketListener>> instance, Object o, ServerPlayerEntity player, Consumer<Packet<ClientPlayPacketListener>> sender) {
        if (o instanceof EntitySpawnS2CPacket packet) {
            return ServerPlayerConnectionEvents.SPAWN_PACKET_FILTER.getInvoker().onSpawnPacketSend(packet, player);
        }

        return true;
    }
}
