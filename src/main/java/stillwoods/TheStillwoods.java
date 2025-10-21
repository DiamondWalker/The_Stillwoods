package stillwoods;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import stillwoods.registry.StillwoodsBlocks;
import stillwoods.registry.StillwoodsItems;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheStillwoods.MODID)
public class TheStillwoods
{
    public static final String MODID = "stillwoods";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TheStillwoods(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();

        StillwoodsItems.register(bus);
        StillwoodsBlocks.register(bus);

        context.registerConfig(ModConfig.Type.COMMON, StillwoodsConfig.SPEC);
    }
}
