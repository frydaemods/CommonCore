package dev.frydae.commoncore.commands;

import dev.frydae.commands.FabricBaseCommand;
import dev.frydae.commands.annotations.*;
import net.minecraft.text.Text;

public class MiscCommands extends FabricBaseCommand {

    @CommandAlias("flyspeed")
    @Description("Change your flying speed")
    public void onFlySpeed(/*@Name("speed") @Description("How fast to go")*/
                               @Completion("range|1-10") @Condition("limits|min=1,max=10") Integer speed) {
        boolean canFly = getPlayer().getAbilities().allowFlying;

        if (canFly) {
            getPlayer().getAbilities().setFlySpeed(speed / 10.0f);
            getPlayer().sendAbilitiesUpdate();
        }
    }

    @CommandAlias("test")
    public void onTest(String potato) {
        reply(Text.literal("ice cream"));
    }
}
