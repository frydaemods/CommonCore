package dev.frydae.beguild.utils.taskchain;

import com.google.common.collect.Lists;
import dev.frydae.beguild.BeGuildCommon;
import lombok.Getter;

import java.util.List;

public final class TaskManager {
    private static TaskManager instance;
    private List<DelayedTask> pendingTasks;
    @Getter private int currentTick = 0;

    private TaskManager() {
        pendingTasks = Lists.newCopyOnWriteArrayList();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            synchronized (TaskManager.class) {
                if (instance == null) {
                    instance = new TaskManager();
                }
            }
        }

        return instance;
    }

    public void tick() {
        handleTasks();

        currentTick++;
    }

    public void addTask(DelayedTask task) {
        pendingTasks.add(task);
    }

    public void addTask(int waitTicks, Runnable runnable) {
        addTask(new DelayedTask(waitTicks, runnable));
    }

    private void handleTasks() {
        for (DelayedTask pendingTask : pendingTasks) {
            if (pendingTask.getRemainingTicks() > 0) {
                pendingTask.tick();
            } else {
                BeGuildCommon.getServer().executeSync(pendingTask.getRunnable());

                pendingTasks.remove(pendingTask);
            }
        }
    }
}
