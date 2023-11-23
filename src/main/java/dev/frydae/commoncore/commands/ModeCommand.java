package dev.frydae.commoncore.commands;

import dev.frydae.commands.CommandUtils;
import dev.frydae.commands.FabricBaseCommand;
import dev.frydae.commands.annotations.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameMode;

@CommandAlias("mode|m")
@Description("Change the gamemode of a player")
public class ModeCommand extends FabricBaseCommand {

    @CommandAlias("gms")
    @Subcommand("survival|s|0")
    public void onSurvival() {
        mode(getPlayer(), getPlayer(), GameMode.SURVIVAL);
    }

    @CommandAlias("gmc")
    @Subcommand("creative|c|1")
    public void onCreative() {
        mode(getPlayer(), getPlayer(), GameMode.CREATIVE);
    }

    @CommandAlias("gma")
    @Subcommand("adventure|a|2")
    public void onAdventure() {
        mode(getPlayer(), getPlayer(), GameMode.ADVENTURE);
    }

    @CommandAlias("gmsp")
    @Subcommand("spectator|sp|3")
    public void onSpectator() {
        mode(getPlayer(), getPlayer(), GameMode.SPECTATOR);
    }

    @Default
    public void onDefault() {
        if (getPlayer().isCreative()) {
            mode(getPlayer(), getPlayer(), GameMode.SURVIVAL);
        } else {
            mode(getPlayer(), getPlayer(), GameMode.CREATIVE);
        }
    }

    private void mode(ServerPlayerEntity sender, ServerPlayerEntity target, GameMode mode) {
        target.changeGameMode(mode);

        MutableText formatted = Text.literal(target.getDisplayName().getString()).append("'s game has been changed to: ").formatted(Formatting.GREEN).append(Text.literal(CommandUtils.ucfirst(mode.getName())).formatted(Formatting.YELLOW));
        sender.sendMessage(formatted, true);
    }
}
