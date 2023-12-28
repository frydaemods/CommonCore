package dev.frydae.beguild.utils.taskchain;

import lombok.Getter;

@Getter
public final class DelayedTask {
    private int remainingTicks;
    private final Runnable runnable;

    public DelayedTask(int remainingTicks, Runnable runnable) {
        this.remainingTicks = remainingTicks;
        this.runnable = runnable;
    }

    public void tick() {
        if (remainingTicks > 0) {
            remainingTicks--;
        }
    }
}
