package stillwoods;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StillwoodsConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<Boolean> PROMOTE_DISCORD_SERVER;
    public static final ForgeConfigSpec.ConfigValue<Boolean> PHOTOSENSITIVE_MODE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> CUSTOM_LIGHTMAP;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("Stillwoods Config");

        PROMOTE_DISCORD_SERVER = builder
                .comment("If true, a link to the Stillwoods Discord server will be posted in chat on world join")
                .define("Send Discord link", true);

        PHOTOSENSITIVE_MODE = builder
                .comment("ENABLE IF YOU SUFFER FROM EPILEPSY!!! Tones down the intensity of visual effects that could potentially induce seizures.")
                .define("Photosensitive mode", false);

        CUSTOM_LIGHTMAP = builder
                .comment("If true, the Sillwoods dimension will use a custom lightmap to make things look even darker")
                .define("Use custom lightmap", false);

        builder.pop();

        SPEC = builder.build();
    }
}
