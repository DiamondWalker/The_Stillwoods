package stillwoods.events;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import stillwoods.registry.StillwoodsDimensions;

import java.util.List;

@Mod.EventBusSubscriber
public class PlayerSleepEvents {
    @SubscribeEvent
    public static void tryToSleep(PlayerSleepInBedEvent event) {
        Player player = event.getEntity();

        if (player.level().dimension() == StillwoodsDimensions.STILLWOODS_LEVEL_KEY) {
            if (player.getRandom().nextInt(10) == 0) {
                event.setResult(Player.BedSleepingProblem.OTHER_PROBLEM);
                player.displayClientMessage(Component.translatable("block.stillwoods.bed.scare"), true);
            }
        }
    }

    @SubscribeEvent
    public static void canSleepHere(SleepingTimeCheckEvent event) {
        if (event.getEntity().level().dimension() == StillwoodsDimensions.STILLWOODS_LEVEL_KEY) event.setResult(Event.Result.ALLOW);
    }
}
