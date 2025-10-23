package stillwoods.rendering.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;

public abstract class MonsterKillScreen extends Screen {
    protected final DeathScreen nextScreen;

    public MonsterKillScreen(DeathScreen nextScreen) {
        super(nextScreen.getTitle());
        this.nextScreen = nextScreen;
    }

    @Override
    public void tick() {
        super.tick();
        if (isDone()) Minecraft.getInstance().setScreen(nextScreen);
    }

    public abstract boolean isDone();
}
