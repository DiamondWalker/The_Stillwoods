package stillwoods.registry;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import stillwoods.ModTeleporter;
import stillwoods.TheStillwoods;

@Mod.EventBusSubscriber(modid = TheStillwoods.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StillwoodsCommands {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(buildCosmeticCommand());
    }

    private static LiteralArgumentBuilder<CommandSourceStack> buildCosmeticCommand() {
        return Commands.literal("teleportToDim")
                .executes(commandContext -> {
                            ServerPlayer sender = commandContext.getSource().getPlayer();
                            MinecraftServer minecraftserver = sender.level().getServer();
                            ResourceKey<Level> resourcekey = sender.level().dimension() == StillwoodsDimensions.STILLWOODS_LEVEL_KEY ?
                                        Level.OVERWORLD : StillwoodsDimensions.STILLWOODS_LEVEL_KEY;

                            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
                            if (portalDimension != null && !sender.isPassenger()) {
                                if(resourcekey == StillwoodsDimensions.STILLWOODS_LEVEL_KEY) {
                                    sender.changeDimension(portalDimension, new ModTeleporter(sender.blockPosition(), true));
                                } else {
                                    sender.changeDimension(portalDimension, new ModTeleporter(sender.blockPosition(), false));
                                }
                            }

                            return 0;
                        }
                    );
    }
}
