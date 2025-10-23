package stillwoods.rendering.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import stillwoods.TheStillwoods;
import stillwoods.util.TextUtil;

import java.util.Random;

public class EyelessKillScreen extends MonsterKillScreen {
    private static final ResourceLocation STATIC_TEXTURE_LOCATION = new ResourceLocation(TheStillwoods.MODID, "textures/gui/static.png");

    int time = 0;


    public EyelessKillScreen(DeathScreen nextScreen) {
        super(nextScreen);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        Random rand = new Random();

        RenderSystem.setShaderColor(0.1f, 0.1f, 0.1f, 1.0f);
        graphics.blit(STATIC_TEXTURE_LOCATION, 0, 0, rand.nextInt(256), rand.nextInt(256), width, height);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        Component[] messages = TextUtil.getTranslatedMessages("gui.stillwoods.death.eyeless");
        boolean italic = rand.nextBoolean();
        int col = rand.nextInt(80);

        graphics.pose().pushPose();
        graphics.pose().scale(1.5F, 1.5F, 1.5F);
        graphics.pose().translate((float) this.width / 2 / 1.5f, (float) height / 2 / 1.5f, 0);

        //TODO: this isn't centered vertically
        graphics.pose().pushPose();
        graphics.pose().translate((rand.nextFloat() - 0.5f) * width / 140, 0, 0);
        for (int i = 0; i < messages.length; i++) {
            Component msg = messages[i];
            if (italic) msg = msg.copy().withStyle(ChatFormatting.ITALIC);
            graphics.drawString(this.font, msg, -font.width(msg) / 2, -font.lineHeight / 2 - (3 - i) * 12, FastColor.ARGB32.color(150, col, col, col), false);
        }
        graphics.pose().popPose();

        for (int i = 0; i < messages.length; i++) {
            Component msg = messages[i];
            if (italic) msg = msg.copy().withStyle(ChatFormatting.ITALIC);
            graphics.drawString(this.font, msg, -font.width(msg) / 2, -font.lineHeight / 2 - (3 - i) * 12, FastColor.ARGB32.color(255, col, col, col), false);
        }
        graphics.pose().popPose();
    }

    @Override
    public void tick() {
        super.tick();
        time++;
    }

    @Override
    public boolean isDone() {
        return time >= 180;
    }
}
