package stillwoods.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import stillwoods.registry.StillwoodsDimensions;
import stillwoods.rendering.gui.EyelessKillScreen;
import stillwoods.rendering.gui.MonsterKillScreen;

import java.util.List;

@Mod.EventBusSubscriber
public class GuiEvents {
    @SubscribeEvent
    public static void setScreen(ScreenEvent.Opening event) {
        if (event.getNewScreen() instanceof DeathScreen deathScreen) {
            if (event.getCurrentScreen() instanceof MonsterKillScreen killScreen) {
                if (!killScreen.isDone()) event.setCanceled(true);
                return;
            }
            if (Minecraft.getInstance().player != null) {
                Player player = Minecraft.getInstance().player;
                if (player.level().dimension() == StillwoodsDimensions.STILLWOODS_LEVEL_KEY) {
                    event.setNewScreen(new EyelessKillScreen(deathScreen));
                }
                //List<Entity> list = player.level().getEntities(player, player.getBoundingBox().inflate(150.0f), (entity -> entity instanceof IKillScreenEntity));
                //if (!list.isEmpty()) event.setNewScreen(((IKillScreenEntity) list.get(0)).getKillScreen(deathScreen));
            }
        }
    }
}
